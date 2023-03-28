package com.example.demo.location;

import com.example.demo.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;
import java.util.HashSet;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "curr_location")

    private Set<Item> currentitems = new HashSet<>();

    private String name;

    public Location(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Location() {

    }

    public Long getId() {
        return id;
    }

    public Set<Item> getCurrentitems() {return currentitems; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
