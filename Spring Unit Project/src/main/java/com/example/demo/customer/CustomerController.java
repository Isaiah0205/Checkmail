package com.example.demo.customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers(){return customerRepository.findAll();}

    @GetMapping(path = "{customerId}")
    public Optional<Customer> getCustomer(@PathVariable("customerId") Long customerId) { return customerRepository.findById(customerId); }

    @PostMapping
    Customer addNewCustomer(@RequestBody Customer customer) { return customerRepository.save(customer);
    }


    @DeleteMapping(path = "{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
    }
}
