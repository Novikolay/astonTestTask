package com.aston.astonTestTask.dto;

import com.aston.astonTestTask.model.Action;
import com.aston.astonTestTask.model.ActionType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ActionMapper implements RowMapper<Action> {
    @Override
    public Action mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Action(
                rs.getInt("id"),
                rs.getTimestamp("dateTime").toLocalDateTime(),
                rs.getInt("billId"),
                rs.getInt("customerId"),
                ActionType.getById(rs.getInt("actionType")),
                rs.getInt("amount")
        );
    }
}
