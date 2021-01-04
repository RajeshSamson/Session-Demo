package com.javasession.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javasession.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Customer> findByEmail(String email);

}
