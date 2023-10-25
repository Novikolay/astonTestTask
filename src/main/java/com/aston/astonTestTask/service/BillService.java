package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Bill;
import com.aston.astonTestTask.model.ServiceException;

import java.util.List;

public interface BillService {
    Bill getBill(int billId);

    List<Bill> getAll();

    Bill create(String userName, int pin, int amount) throws ServiceException;

    Bill receive(String number, int pin, int amount) throws ServiceException;

    Bill deposit(String number, int amount) throws ServiceException;

    List<Bill> transfer(String outNumber, int pin, int amount, String inNumber) throws ServiceException;

}
