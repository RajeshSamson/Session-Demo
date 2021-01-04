package com.javasession.controller;

import com.javasession.model.Customer;
import com.javasession.repository.CustomerRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // GET
    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    // GET By FirstName and LastName
    @GetMapping("/customer/{firstName}/{lastName}")
    public List<Customer> getCustomerByName(@PathVariable String firstName, @PathVariable String lastName,
                                            @RequestParam(name = "email", required = false) String email) {
        if (email != null) {
            return customerRepository.findByEmail(email);
        } else {
            return customerRepository.findByFirstNameAndLastName(firstName, lastName);
        }
    }

    // POST
    @PostMapping("/customer")
    public String saveCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return "Success";
    }

    // DELETE
    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable long id) {
        customerRepository.deleteById(id);
        return "Successfully Deleted the Customer";
    }

}
