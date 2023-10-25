package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Action;
import com.aston.astonTestTask.model.ActionSearchCriteria;
import com.aston.astonTestTask.model.Customer;
import com.aston.astonTestTask.model.CustomerSearchCriteria;
import com.aston.astonTestTask.repository.ActionRepository;
import com.aston.astonTestTask.utils.CustomerUtils;
import com.aston.astonTestTask.utils.SearchUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Primary
@Service
@AllArgsConstructor
public class ActionServiceImpl implements ActionService {
    private final ActionRepository actionRepository;
    private final SearchUtils searchUtils;
    private final CustomerUtils customerUtils;

    @Override
    public List<Action> getAll() {
        return actionRepository.getAll();
    }

    @Override
    public List<Action> clientHistory(String name, int pin) {
        CustomerSearchCriteria searchCriteria = new CustomerSearchCriteria(name);
        List<Customer> customers = searchUtils.search(searchCriteria);

        if (CollectionUtils.isNotEmpty(customers)) {
            if (!customerUtils.checkPin(customers.get(0), pin)) {
                //TODO: сообщение о неверном пароле
                return null;
            }

            return search(new ActionSearchCriteria(null, customers.get(0).getId()));
        } else {
            //TODO: данного пользователя не существует
            return null;
        }
    }

    public List<Action> search(ActionSearchCriteria searchCriteria) {
        if (isNull(searchCriteria)) {
            return actionRepository.findAll();
        }

        Specification<Action> specification = Specification.where(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and()));

        if (nonNull(searchCriteria.getBillId())) {
            specification = specification.and(((root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("billId"), searchCriteria.getBillId())));
        }

        if (nonNull(searchCriteria.getCustomerId())) {
            specification = specification.and(((root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("customerId"), searchCriteria.getCustomerId())));
        }

        return actionRepository.findAll(specification);
    }

}
