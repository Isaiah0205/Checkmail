package com.example.demo.item;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.location.Location;
import com.example.demo.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "api/v1/item")
public class ItemController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Item> getItem(@PathVariable("id") Long id) { return itemRepository.findById(id); }

    @PostMapping
    public void registerNewItem(@RequestBody Item item) {
        itemRepository.save(item);
    }

    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable("id")Long id) {
        itemRepository.deleteById(id);
    }

    @PutMapping("/{itemsid}/location/{locationid}")
    Item currentItemToLocation(
            @PathVariable Long locationid,
            @PathVariable Long itemsid
    ) {
        Item item = itemRepository.findById(itemsid).get();
        Location location = locationRepository.findById(locationid).get();
        item.currentItems(location);
        return itemRepository.save(item);
    }

    @PutMapping("/{itemsid}/customer/{customerid}")
    Item currentItemToCustomer(
            @PathVariable Long customerid,
            @PathVariable Long itemsid
    ) {
        Customer customer = customerRepository.findById(customerid).get();
        Item item = itemRepository.findById(itemsid).get();
        item.assignCustomer(customer);
        return itemRepository.save(item);
    }
}