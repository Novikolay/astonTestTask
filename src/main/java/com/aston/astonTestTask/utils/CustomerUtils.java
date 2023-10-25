package com.aston.astonTestTask.utils;

import com.aston.astonTestTask.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerUtils {

    public Boolean checkPin(Customer customer, int pin) {
        return customer.getPin() == (pin);
    }
}
