package com.ankit.springjoin.repository;

import com.ankit.springjoin.dto.OrderResponse;
import com.ankit.springjoin.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT new com.ankit.springjoin.dto.OrderResponse(c.name, p.productName) FROM Customer c JOIN c.products p")
        public List<OrderResponse> getinformation();
}
