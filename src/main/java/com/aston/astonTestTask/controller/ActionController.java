package com.aston.astonTestTask.controller;

import com.aston.astonTestTask.model.Action;
import com.aston.astonTestTask.model.ServiceException;
import com.aston.astonTestTask.service.ActionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/actions")
@AllArgsConstructor
public class ActionController {

    private final ActionService actionService;

    @GetMapping(value = "/all")
    public List<Action> getAll() {
        return actionService.getAll();
    }

    @ApiOperation(
            value = "Получение информации по истории транзакций клиента",
            notes = "Имя пользователя | PIN-code"
    )
    @PostMapping(value = "/clientHistory")
    public ResponseEntity<List<Action>> clientHistory(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "pin") Integer pin) throws ServiceException {
        log.info("Get client history for client with id {} and pin {}", name, pin);
        return ResponseEntity.ok(actionService.clientHistory(name, pin));
    }
}
