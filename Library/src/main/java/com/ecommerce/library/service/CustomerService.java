package com.ecommerce.library.service;

import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CustomerService {
    Customer save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer update(CustomerDto customerDto);

    Customer changePass(CustomerDto customerDto);

    CustomerDto getCustomer(String username);

    List<CustomerDto> customers();

    List<CustomerDto> allCustomers();

    Page<CustomerDto> getAllCustomer(int pageNo);

    void deleteById(Long id);
}
