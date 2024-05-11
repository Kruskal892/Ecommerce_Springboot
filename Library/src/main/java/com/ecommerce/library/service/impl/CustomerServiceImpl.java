package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Role;
import com.ecommerce.library.repository.CustomerRepository;
import com.ecommerce.library.repository.RoleRepository;
import com.ecommerce.library.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<CustomerDto> customers() {
        return transferData(customerRepository.getAllBCustomer());
    }

    @Override
    public List<CustomerDto> allCustomers() {
        List<Customer> customer = customerRepository.findAll();
        List<CustomerDto> customerDtos = transferData(customer);
        return customerDtos;
    }


    @Override
    public Page<CustomerDto> getAllCustomer(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 6);
        List<CustomerDto> customerDtoList = this.allCustomers();
        Page<CustomerDto> customerDtoPage = toPage(customerDtoList, pageable);
        return customerDtoPage;
    }
    @Override
    public void deleteById(Long id) {
        // Check if the user exists
        Optional<Customer> userOptional = customerRepository.findById(id);
        if (userOptional.isPresent()) {
            // If the user exists, remove their roles
            Customer user = userOptional.get();
            user.getRoles().clear(); // Clear all roles

            // Save the user without roles
            customerRepository.save(user);

            // Now you can safely delete the user
            customerRepository.deleteById(id);
        } else {
            // Handle case where user with given id doesn't exist
            // You can throw an exception or handle it based on your application's logic
        }
    }


    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPassword(customerDto.getPassword());
        customer.setUsername(customerDto.getUsername());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setUsername(customerDto.getUsername());
        customer.setRoles(Arrays.asList(roleRepository.findByName("CUSTOMER")));
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public CustomerDto getCustomer(String username) {
        CustomerDto customerDto = new CustomerDto();
        Customer customer = customerRepository.findByUsername(username);
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setUsername(customer.getUsername());
        customerDto.setPassword(customer.getPassword());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setCity(customer.getCity());
        customerDto.setCountry(customer.getCountry());
        return customerDto;
    }

    @Override
    public Customer changePass(CustomerDto customerDto) {
        Customer customer = customerRepository.findByUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(CustomerDto dto) {
        Customer customer = customerRepository.findByUsername(dto.getUsername());
        customer.setAddress(dto.getAddress());
        customer.setCity(dto.getCity());
        customer.setCountry(dto.getCountry());
        customer.setPhoneNumber(dto.getPhoneNumber());
        return customerRepository.save(customer);
    }


    private Page toPage(List list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }

    private List<CustomerDto> transferData(List<Customer> customers) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customer.getId());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setAddress(customer.getAddress());
            customerDto.setUsername(customer.getUsername());
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }

}
