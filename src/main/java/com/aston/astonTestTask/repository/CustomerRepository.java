package com.aston.astonTestTask.repository;

import com.aston.astonTestTask.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends
        JpaRepository<Customer, String>,
        JpaSpecificationExecutor<Customer> {
    Optional<Customer> getById(int id);

    List<Customer> getAll();

    void insertWithEntityManager(Customer customer);
}