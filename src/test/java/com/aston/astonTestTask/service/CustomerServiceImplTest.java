package com.aston.astonTestTask.service;

import com.aston.astonTestTask.model.Customer;
import com.aston.astonTestTask.repository.CustomerRepository;
import com.aston.astonTestTask.service.CustomerServiceImpl;
import com.aston.astonTestTask.utils.CustomerUtils;
import com.aston.astonTestTask.utils.SearchUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerUtils customerUtils;

    @Mock
    private SearchUtils searchUtils;

    @Mock
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void getCustomer() {
//        int id = anyInt();
//        when(customerRepository.findById(String.valueOf(id))).thenReturn(Optional.of(new Customer()));
//
//        customerService.getCustomer(1);
//
//        verify(customerRepository, times(1)).findById(String.valueOf(id));
//    }

    @Test
    void create() {

    }

    @Test
    void clientAccounts() {
    }
}