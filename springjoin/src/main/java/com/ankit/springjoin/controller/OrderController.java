package com.ankit.springjoin.controller;

import com.ankit.springjoin.dto.OrderRequest;
import com.ankit.springjoin.dto.OrderResponse;
import com.ankit.springjoin.entity.Customer;
import com.ankit.springjoin.repository.CustomerRepository;
import com.ankit.springjoin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody OrderRequest request) {
        return customerRepository.save(request.getCustomer());
    }
    @GetMapping("/findAllOrders")
    public List<Customer> findAllOrders() {
        return customerRepository.findAll();
    }

    @GetMapping("/getJoinInfo")
    public List<OrderResponse> findJoinData() {
        return customerRepository.getinformation();
    }

}
