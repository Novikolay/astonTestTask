package com.aston.astonTestTask.repository;

import com.aston.astonTestTask.model.Action;
import com.aston.astonTestTask.model.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface ActionRepository extends
        JpaRepository<Action, String>,
        JpaSpecificationExecutor<Action> {
    void addAction(LocalDateTime dateTime, int billId, ActionType action, int amount);

    List<Action> getAll();
}
