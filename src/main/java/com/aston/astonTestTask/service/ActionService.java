package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Action;
import com.aston.astonTestTask.model.ServiceException;

import java.util.List;

public interface ActionService {
    List<Action> getAll();

    List<Action> clientHistory(String name, int pin) throws ServiceException;
}

