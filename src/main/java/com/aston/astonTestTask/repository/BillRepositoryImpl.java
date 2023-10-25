package com.aston.astonTestTask.repository;

import com.aston.astonTestTask.dto.BillMapper;
import com.aston.astonTestTask.model.Bill;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class BillRepositoryImpl implements BillRepository {

    private static final String SQL_GET_BILL_BY_ID =
            "select id, customerId, amount from bills where id = :id";
    private static final String SQL_GET_ALL_BILLS =
            "select * from bills";
    private final BillMapper billMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithEntityManager(Bill bill) {
        this.entityManager.persist(bill);
    }

    @Transactional
    public void updateWithEntityManager(Bill bill) {
        this.entityManager.merge(bill);
    }

    @Override
    public Optional<Bill> getById(int id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.query(
                        SQL_GET_BILL_BY_ID,
                        params,
                        billMapper
                ).stream()
                .findFirst();
    }

    @Override
    public List<Bill> getAll() {
        return new ArrayList<>(jdbcTemplate.query(
                SQL_GET_ALL_BILLS,
                billMapper
        ));
    }

    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public List<Bill> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Bill> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Bill bill) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Bill> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Bill> S save(S s) {
        return null;
    }

    @Override
    public <S extends Bill> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Bill> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Bill> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Bill> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Bill> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Bill getOne(String s) {
        return null;
    }

    @Override
    public Bill getById(String s) {
        return null;
    }

    @Override
    public <S extends Bill> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Bill> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Bill> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Bill> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Bill> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Bill> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<Bill> findOne(Specification<Bill> specification) {
        return Optional.empty();
    }

    @Override
    public List<Bill> findAll(Specification<Bill> specification) {
        return null;
    }

    @Override
    public Page<Bill> findAll(Specification<Bill> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<Bill> findAll(Specification<Bill> specification, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<Bill> specification) {
        return 0;
    }
}
