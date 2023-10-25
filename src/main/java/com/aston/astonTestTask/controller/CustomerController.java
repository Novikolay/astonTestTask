package com.aston.astonTestTask.controller;

import com.aston.astonTestTask.model.Bill;
import com.aston.astonTestTask.model.Customer;
import com.aston.astonTestTask.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/{customerId:\\d+}")
    public Customer getCustomer(@PathVariable int customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping(value = "/all")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @ApiOperation(
            value = "Создание нового пользователя",
            notes = "Имя пользователя | PIN-code"
    )
    @PostMapping(value = "/create")
    public ResponseEntity<Customer> create(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "pin") Integer pin) {
        log.info("Create user with name: {}, code: {} and start amount: {}", name, pin);
        return ResponseEntity.ok(customerService.create(name, pin));
    }

    @ApiOperation(
            value = "Получение информации по счетам клиента",
            notes = "Имя пользователя | PIN-code"
    )
    @PostMapping(value = "/clientAccounts")
    public ResponseEntity<List<Bill>> clientAccounts(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "pin") Integer pin) {
        log.info("Get client accounts for client with id {} and pin {}", name, pin);
        return ResponseEntity.ok(customerService.clientAccounts(name, pin));
    }

}
