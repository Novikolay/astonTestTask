package com.aston.astonTestTask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActionType {
    DEBIT(1),
    CREDIT(2);

    private int id;

    public static ActionType getById(int id) {
        for (ActionType actionType : values()) {
            if (actionType.getId() == id) {
                return actionType;
            }
        }
        throw new RuntimeException("Unknown action type: " + id);
    }

}
