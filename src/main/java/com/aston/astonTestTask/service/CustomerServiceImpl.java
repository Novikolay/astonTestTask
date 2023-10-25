package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Bill;
import com.aston.astonTestTask.model.BillSearchCriteria;
import com.aston.astonTestTask.model.Customer;
import com.aston.astonTestTask.model.CustomerSearchCriteria;
import com.aston.astonTestTask.repository.CustomerRepository;
import com.aston.astonTestTask.utils.CustomerUtils;
import com.aston.astonTestTask.utils.EntityNotFoundException;
import com.aston.astonTestTask.utils.SearchUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Primary
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerUtils customerUtils;
    private final SearchUtils searchUtils;

    @Override
    public Customer getCustomer(int customerId) {
        return customerRepository.getById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(customerId));
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Customer create(String name, int pin) {
        CustomerSearchCriteria searchCriteria = new CustomerSearchCriteria(name);
        List<Customer> customers = searchUtils.search(searchCriteria);

        if (CollectionUtils.isNotEmpty(customers)) {
            // TODO: данный пользователь уже существует
            return null;
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setPin(pin);
        customerRepository.insertWithEntityManager(customer);

        return customer;
    }

    @Override
    public List<Bill> clientAccounts(String name, int pin) {
        CustomerSearchCriteria searchCriteria = new CustomerSearchCriteria(name);
        List<Customer> customers = searchUtils.search(searchCriteria);

        if (CollectionUtils.isNotEmpty(customers)) {
            if (!customerUtils.checkPin(customers.get(0), pin)) {
                //TODO: сообщение о неверном пароле
                return null;
            }

            BillSearchCriteria billSearchCriteria = new BillSearchCriteria(null, customers.get(0));
            return searchUtils.search(billSearchCriteria);
        } else {
            //TODO: некорректное имя пользователя
            return null;
        }
    }

}
