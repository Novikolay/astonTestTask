package com.aston.astonTestTask.dto;

import com.aston.astonTestTask.model.Bill;
import com.aston.astonTestTask.repository.CustomerRepository;
import com.aston.astonTestTask.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@AllArgsConstructor
public class BillMapper implements RowMapper<Bill> {

    private CustomerRepository customerRepository;

    @Override
    public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Bill(
                rs.getInt("id"),
                rs.getString("number"),
                rs.getInt("amount"),
                customerRepository.getById(rs.getInt("customerId")).get()
        );
    }
}
