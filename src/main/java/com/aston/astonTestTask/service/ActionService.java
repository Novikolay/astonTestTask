package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Action;

import java.util.List;

public interface ActionService {
    List<Action> getAll();

    List<Action> clientHistory(String name, int pin);
}

