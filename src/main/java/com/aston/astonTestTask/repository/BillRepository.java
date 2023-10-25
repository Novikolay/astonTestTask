package com.aston.astonTestTask.repository;

import com.aston.astonTestTask.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface BillRepository extends
        JpaRepository<Bill, String>,
        JpaSpecificationExecutor<Bill> {
    Optional<Bill> getById(int id);
    List<Bill> getAll();

    void insertWithEntityManager(Bill bill);
    void updateWithEntityManager(Bill bill);


}

