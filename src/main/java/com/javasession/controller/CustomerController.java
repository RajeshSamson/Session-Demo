package com.javasession.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasession.CustomerRepository;
import com.javasession.model.Customer;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	// GET
	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		return customers;
	}

	// GET By FirstName and LastName
	@GetMapping("/customer/{firstName}/{lastName}")
	public List<Customer> getCustomerByName(@PathVariable String firstName, @PathVariable String lastName) {
		return customerRepository.findByFirstNameAndLastName(firstName, lastName);
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
