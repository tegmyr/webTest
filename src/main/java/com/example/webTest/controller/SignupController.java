package com.example.webTest.controller;

import com.example.webTest.dto.UserDto;
import com.example.webTest.repositories.CustomerRepository;
import com.example.webTest.repositories.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    //private final CustomerRepository customerRepository;
    //private final RoomRepository roomRepository;

    public SignupController(CustomerRepository customerRepository, RoomRepository roomRepository){
        //this.customerRepository = customerRepository;
        //this.roomRepository = roomRepository;

    }




    @GetMapping("/signup")
     public String signup(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);

        return "signup";
    }

    @PostMapping("/signup")
    public String pSignup(@ModelAttribute("user")  UserDto userDto){

        System.out.println("Förnamn: " + userDto.getFirstName());
        System.out.println("Efternamn " + userDto.getLastName());

        return "redirect:/login";
    }




}

