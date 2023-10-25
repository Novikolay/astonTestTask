package com.aston.astonTestTask.utils;

import com.aston.astonTestTask.model.Bill;
import com.aston.astonTestTask.model.BillSearchCriteria;
import com.aston.astonTestTask.model.Customer;
import com.aston.astonTestTask.model.CustomerSearchCriteria;
import com.aston.astonTestTask.repository.BillRepository;
import com.aston.astonTestTask.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class SearchUtils {

    private final CustomerRepository customerRepository;
    private final BillRepository billRepository;

    public List<Customer> search(CustomerSearchCriteria searchCriteria) {
        if (isNull(searchCriteria)) {
            return customerRepository.findAll();
        }

        Specification<Customer> specification = Specification.where(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and()));

        if (nonNull(searchCriteria.getName())) {
            specification = specification.and(((root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("name"), searchCriteria.getName())));
        }

        return customerRepository.findAll(specification);
    }

    public List<Bill> search(BillSearchCriteria searchCriteria) {
        if (isNull(searchCriteria)) {
            return billRepository.findAll();
        }

        Specification<Bill> specification = Specification.where(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and()));

        if (nonNull(searchCriteria.getNumber())) {
            specification = specification.and(((root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("number"), searchCriteria.getNumber())));
        }

        if (nonNull(searchCriteria.getCustomer())) {
            specification = specification.and(((root, criteriaQuery, criteriaBuilder)
                    -> criteriaBuilder.equal(root.get("customer"), searchCriteria.getCustomer())));
        }

        return billRepository.findAll(specification);
    }
}
