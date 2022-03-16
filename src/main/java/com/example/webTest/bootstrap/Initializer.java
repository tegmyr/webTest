package com.example.webTest.bootstrap;

import com.example.webTest.model.Customer;
import com.example.webTest.model.Room;
import com.example.webTest.repositories.CustomerRepository;
import com.example.webTest.repositories.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Initializer implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    public Initializer(CustomerRepository customerRepository, RoomRepository roomRepository){
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hit kom vi iallafall");
        Room lekt1 = new Room("D4", 4);
        Room lekt2 = new Room("D3", 4);
        Room lekt3 = new Room("A1",0);
        Customer cust1 = new Customer("Lennart", "Helsing");
        Customer cust2 = new Customer("Astrid", "Lindgren");
        Customer cust3 = new Customer("Harald", "Treutiger");

        // Connect Astrid to D4
        cust2.getRooms().add(lekt1);
        lekt1.getCustomers().add(cust2);

        //connect Harald v A1
        cust3.getRooms().add(lekt3);
        lekt3.getCustomers().add(cust3);

        // connect Harald and D4
        cust3.getRooms().add(lekt1);
        lekt1.getCustomers().add(cust3);

        customerRepository.save(cust1);
        customerRepository.save(cust2);
        customerRepository.save(cust3);

        roomRepository.save(lekt1);
        roomRepository.save(lekt2);
        roomRepository.save(lekt3);

        System.out.println("Number of customers: " + customerRepository.count());
        System.out.println("Number of rooms" + + roomRepository.count());


    }
}
