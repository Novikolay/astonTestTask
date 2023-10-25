package com.aston.astonTestTask.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    private final int entityId;

    @Override
    public String getMessage() {
        return "Entity with id = " + entityId + " not found";
    }
}

