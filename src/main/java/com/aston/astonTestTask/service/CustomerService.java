package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Bill;
import com.aston.astonTestTask.model.Customer;
import com.aston.astonTestTask.model.ServiceException;

import java.util.List;

public interface CustomerService {
    Customer getCustomer(int customerId);

    List<Customer> getAll();

    Customer create(String name, int pin) throws ServiceException;

    List<Bill> clientAccounts(String name, int pin) throws ServiceException;
}
