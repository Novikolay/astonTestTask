package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Bill;

import java.util.List;

public interface BillService {
    Bill getBill(int billId);

    List<Bill> getAll();

    Bill create(String userName, int pin, int amount);

    Bill receive(String number, int pin, int amount);

    Bill deposit(String number, int amount);

    List<Bill> transfer(String outNumber, int pin, int amount, String inNumber);

}
