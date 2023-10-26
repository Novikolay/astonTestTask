package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.*;
import com.aston.astonTestTask.repository.ActionRepository;
import com.aston.astonTestTask.repository.BillRepository;
import com.aston.astonTestTask.utils.CustomerUtils;
import com.aston.astonTestTask.utils.EntityNotFoundException;
import com.aston.astonTestTask.utils.SearchUtils;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Log
@Primary
@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final ActionRepository actionRepository;
    private final CustomerService customerService;

    private final CustomerUtils customerUtils;
    private final SearchUtils searchUtils;

    @Override
    public Bill getBill(int billId) {
        return billRepository.getById(billId)
                .orElseThrow(() -> new EntityNotFoundException(billId));
    }

    @Override
    public List<Bill> getAll() {
        return billRepository.getAll();
    }

    /**
     * Проверяем наличие пользователя
     * Проверяем пин
     * Если нет -- создаем пользователя, затем добавляем счет
     * Если есть -- создаем счет уже существующему пользователю
     *
     * @param userName
     * @param pin
     * @param amount
     * @return
     */
    @Override
    @Transactional
    public Bill create(String userName, int pin, int amount) throws ServiceException {
        CustomerSearchCriteria searchCriteria = new CustomerSearchCriteria(userName);
        List<Customer> customers = searchUtils.search(searchCriteria);

        if (CollectionUtils.isNotEmpty(customers)) {
            if (!customerUtils.checkPin(customers.get(0), pin)) {
                throw new ServiceException("Некорректный пароль");
            }

            // создаем счет уже существующему пользователю
            return create(customers.get(0), amount);
        } else {
            // заводим нового пользователя и генерим ему счет
            Customer customer = customerService.create(userName, pin);
            return create(customer, amount);
        }
    }

    private Bill create(Customer customer, int amount) {
        String dateTime = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault()).format(new Date());

        Bill bill = new Bill();
        bill.setNumber(String.format("BILL-%s", dateTime));
        bill.setAmount(amount);
        bill.setCustomer(customer);
        billRepository.insertWithEntityManager(bill);
        return bill;
    }

    @Override
    public Bill receive(String number, int pin, int amount) throws ServiceException {
        BillSearchCriteria searchCriteria = new BillSearchCriteria(number, null);
        List<Bill> bills = searchUtils.search(searchCriteria);

        if (CollectionUtils.isNotEmpty(bills)) {
            if (!customerUtils.checkPin(bills.get(0).getCustomer(), pin)) {
                throw new ServiceException("Некорректный пароль");
            }

            Bill bill = bills.get(0);
            if (bill.getAmount() >= amount) {
                int updAmount = bill.getAmount() - amount;
                bill.setAmount(updAmount);
                billRepository.updateWithEntityManager(bill);
                return bill;
            } else {
                throw new ServiceException("На указанном счете недостаточно средств для снятия");
            }
        } else {
            throw new ServiceException("По вашему номеру счетов не найдено");
        }
    }

    @Override
    public Bill deposit(String number, int amount) throws ServiceException {

        BillSearchCriteria searchCriteria = new BillSearchCriteria(number, null);
        List<Bill> bills = searchUtils.search(searchCriteria);

        if (CollectionUtils.isNotEmpty(bills)) {
            Bill bill = bills.get(0);
            int updAmount = bill.getAmount() + amount;
            bill.setAmount(updAmount);
            billRepository.updateWithEntityManager(bill);
            return bill;
        } else {
            throw new ServiceException("По вашему номеру счетов не найдено");
        }
    }

    @Override
    public List<Bill> transfer(String outNumber, int pin, int amount, String inNumber) throws ServiceException {
        List<Bill> billsOut = searchUtils.search(new BillSearchCriteria(outNumber, null));
        if (CollectionUtils.isEmpty(billsOut)) {
            throw new ServiceException("По вашему номеру отправителя счетов не найдено");
        }

        List<Bill> billsIn = searchUtils.search(new BillSearchCriteria(inNumber, null));
        if (CollectionUtils.isEmpty(billsIn)) {
            throw new ServiceException("По вашему номеру получателя счетов не найдено");
        }

        if (!customerUtils.checkPin(billsOut.get(0).getCustomer(), pin)) {
            throw new ServiceException("Некорректный пароль");
        }

        Bill credit = billsOut.get(0);
        Bill debit = billsIn.get(0);

        credit.setAmount(credit.getAmount() - amount);
        debit.setAmount(debit.getAmount() + amount);

        logActions(credit.getId(), debit.getId(), amount);

        return updateBills(credit, debit);
    }

    private List<Bill> updateBills(Bill credit, Bill debit) {
        List<Bill> billList = new ArrayList<>();
        billList.add(credit);
        billList.add(debit);

        billRepository.updateWithEntityManager(credit);
        billRepository.updateWithEntityManager(debit);
        return billList;
    }

    private void logActions(int creditId, int debitId, long amount) {
        LocalDateTime dateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay();
        actionRepository.addAction(dateTime, creditId, ActionType.CREDIT, (int) amount);
        actionRepository.addAction(dateTime, debitId, ActionType.DEBIT, (int) amount);
    }

}