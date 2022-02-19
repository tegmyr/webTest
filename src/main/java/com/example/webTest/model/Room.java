package com.example.webTest.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name; // Should be unique
    private String description;
    private int floor;

    @ManyToMany
    @JoinTable(name="room_customer",
            joinColumns = @JoinColumn(name="room_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id"))
    private Set<Customer> customers;

    protected Room(){}

    public Room(String name, int floor) {
        this.floor = floor;
        this.name = name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        return customers != null ? customers.equals(room.customers) : room.customers == null;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", floor=" + floor +
                ", customers=" + customers +
                '}';
    }
    /* Just getters and setters below*/

    public void setFloor(int floor){
        this.floor = floor;
    }

    public int getFloor(){
        return this.floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public long getId(){
        return this.id;
    }

    /* Uncertain about these! we get the reference of the actual object */
    public  Set<Customer> getCustomers(){
        return customers;
    }

    public void setCustomers(Set<Customer> customers){
        this.customers = customers;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }



}
