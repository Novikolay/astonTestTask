package com.aston.astonTestTask.controller;

import com.aston.astonTestTask.model.Bill;
import com.aston.astonTestTask.model.ServiceException;
import com.aston.astonTestTask.service.BillService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/bills")
@AllArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping(value = "/{billId:\\d+}")
    public Bill getBill(@PathVariable int billId) {
        return billService.getBill(billId);
    }

    @GetMapping(value = "/all")
    public List<Bill> getAll() {
        return billService.getAll();
    }

    @ApiOperation(
            value = "Создание нового счета",
            notes = "Имя пользователя | PIN-code | Стартовая сумма"
    )
    @PostMapping(value = "/create")
    public ResponseEntity<Bill> create(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "pin") Integer pin,
            @RequestParam(name = "amount") Integer amount) throws ServiceException {
        log.info("Create user with name: {}, code: {} and start amount: {}", name, pin, amount);
        return ResponseEntity.ok(billService.create(name, pin, amount));
    }

    @ApiOperation(
            value = "Снятие средств со счета",
            notes = "№ счета | PIN-code | Сумма"
    )
    @PostMapping(value = "/receive")
    public ResponseEntity<Bill> receive(
            @RequestParam(name = "number") String number,
            @RequestParam(name = "pin") Integer pin,
            @RequestParam(name = "amount") Integer amount) throws ServiceException {
        log.info("Receiving funds in amount {} from bill {} with code {}", amount, number, pin);
        return ResponseEntity.ok(billService.receive(number, pin, amount));
    }

    @ApiOperation(
            value = "Зачисление средств на счет",
            notes = "№ счета | Сумма"
    )
    @PostMapping(value = "/deposit")
    public ResponseEntity<Bill> deposit(
            @RequestParam(name = "number") String number,
            @RequestParam(name = "amount") Integer amount) throws ServiceException {
        log.info("Deposit amount {} to bill with number {}", amount, number);
        return ResponseEntity.ok(billService.deposit(number, amount));
    }

    @ApiOperation(
            value = "Проводка средств между счетами",
            notes = "№ счета отправителя | PIN-code | Сумма | № счета получателя"
    )
    @PostMapping(value = "/transfer")
    public ResponseEntity<List<Bill>> transfer(
            @RequestParam(name = "outNumber") String outNumber,
            @RequestParam(name = "pin") Integer pin,
            @RequestParam(name = "amount") Integer amount,
            @RequestParam(name = "inNumber") String inNumber) throws ServiceException {
        log.info("Transfer money {} from outNumber {} with pin {} to inNumber {}", amount, outNumber, pin, inNumber);
        return ResponseEntity.ok(billService.transfer(outNumber, pin, amount, inNumber));
    }
}
