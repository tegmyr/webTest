package com.example.webTest;


import com.example.webTest.model.Privilege;
import com.example.webTest.model.Role;
import com.example.webTest.model.User;
import com.example.webTest.repositories.PrivilegeRepository;
import com.example.webTest.repositories.RoleRepository;
import com.example.webTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Taken more or less directly from
 * https://www.baeldung.com/role-and-privilege-for-spring-security-registration
 *
 * The Idea  is to setup the User - Role - Privilege combination at application startup
 *
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege,writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("TEst");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);
        alreadySetup = true;

    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name){
      Privilege privilege = privilegeRepository.findByName(name);
      if(privilege == null){
          privilege = new Privilege(name);
          privilegeRepository.save(privilege);

      }
      return privilege;
    }

    Role createRoleIfNotFound(String name,  Collection<Privilege> privileges){
        Role role = roleRepository.findByName(name);
        if(role == null){
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }

        return role;
    }



}
