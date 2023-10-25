package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Bill;
import com.aston.astonTestTask.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(int customerId);

    List<Customer> getAll();

    Customer create(String name, int pin);

    List<Bill> clientAccounts(String name, int pin);
}
