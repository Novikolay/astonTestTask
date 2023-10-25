package com.aston.astonTestTask.repository;

import com.aston.astonTestTask.dto.ActionMapper;
import com.aston.astonTestTask.model.Action;
import com.aston.astonTestTask.model.ActionType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ActionRepositoryImpl implements ActionRepository {
    private static final String SQL_ADD_ACTION =
            "insert into actions (dateTime, billId, actionType, amount) " +
                    "values (:dateTime, :billId, :actionId, :amount)";
    private static final String SQL_GET_ACTION_HISTORY =
            "select * from actions";

    private ActionMapper actionMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void addAction(LocalDateTime dateTime, int billId, ActionType action, int amount) {
        var params = new MapSqlParameterSource();
        params.addValue("dateTime", Timestamp.valueOf(dateTime));
        params.addValue("billId", billId);
        params.addValue("actionId", action.getId());
        params.addValue("amount", amount);
        jdbcTemplate.update(SQL_ADD_ACTION, params);
    }

    @Override
    public List<Action> getAll() {
        return new ArrayList<>(jdbcTemplate.query(
                SQL_GET_ACTION_HISTORY,
                actionMapper
        ));
    }


    @Override
    public List<Action> findAll() {
        return null;
    }

    @Override
    public List<Action> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Action> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Action> findAllById(Iterable<String> iterable) {
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
    public void delete(Action action) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Action> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Action> S save(S s) {
        return null;
    }

    @Override
    public <S extends Action> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Action> findById(String s) {
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
    public <S extends Action> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Action> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Action> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Action getOne(String s) {
        return null;
    }

    @Override
    public Action getById(String s) {
        return null;
    }

    @Override
    public <S extends Action> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Action> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Action> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Action> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Action> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Action> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<Action> findOne(Specification<Action> specification) {
        return Optional.empty();
    }

    @Override
    public List<Action> findAll(Specification<Action> specification) {
        return null;
    }

    @Override
    public Page<Action> findAll(Specification<Action> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<Action> findAll(Specification<Action> specification, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<Action> specification) {
        return 0;
    }
}