package com.example.webTest.repositories;

import com.example.webTest.model.Privilege;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
Privilege findByName(String name);
}
