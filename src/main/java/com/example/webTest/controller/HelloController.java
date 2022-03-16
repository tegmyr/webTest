package com.example.webTest.controller;

import com.example.webTest.model.Customer;
import com.example.webTest.model.Room;
import com.example.webTest.repositories.CustomerRepository;
import com.example.webTest.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class HelloController {

    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    public HelloController(CustomerRepository customerRepository, RoomRepository roomRepository){
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;

    }




    @GetMapping("/")
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

    @GetMapping("/users/{id}")
    //@RequestMapping(value = "message", method = RequestMethod.GET)
    public String getRoomsPerUser(long id, Model model){
        Customer cust = customerRepository.findById(id);
        Set<Room> userRoom = cust.getRooms();
         model.addAttribute("name",cust.getFirstName()) ;
         model.addAttribute("rooms", cust.getRooms());
        return "roomPerUser";
    }

    @PostMapping("/adduser")
    public String addCustomer(Customer customer, BindingResult result, Model model){
        System.out.println("Kom hela vägen hit");
        customerRepository.save(customer);
        return "redirect:/users";

    }
    @GetMapping("/login")
    public String login(){

        System.out.println("Kom till login");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){

        System.out.println("Kom till logout");
        return "logout";
    }



}

