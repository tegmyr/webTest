package com.example.webTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("title", "Nu börjar vi");
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/users")
    //@RequestMapping(value = "message", method = RequestMethod.GET)
    public String getUsers(Model model){
       model.addAttribute("messages",customerRepository.findAll()) ;

        return "users";
    }


}

