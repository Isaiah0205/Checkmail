package com.example.demo.item;

import com.example.demo.customer.Customer;
import com.example.demo.location.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Item {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long id;

    @ManyToMany
    @JoinTable(
            name="curr_location",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> curr_location  = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "customer_id", referencedColumnName = "id")
    private Customer customer;

    private String name;

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item() {

    }

    public Set<Location> getCurr_location() {
        return curr_location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {return customer;}

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name=" + name + '\'' +
                '}';
    }

    public void currentItems(Location location) { curr_location.add(location); }

    public void assignCustomer(Customer customer) { this.customer = customer; }
}