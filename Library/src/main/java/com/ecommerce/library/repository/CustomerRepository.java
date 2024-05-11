package com.ecommerce.library.repository;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);

    @Query("select c from Customer c where c.is_deleted = false and c.is_activated = true")
    List<Customer> getAllBCustomer();

    List<Customer> findByRolesContains(Role role);
}
