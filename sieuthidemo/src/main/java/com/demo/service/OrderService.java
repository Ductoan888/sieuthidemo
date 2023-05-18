package com.demo.service;

import java.util.List;

import com.demo.dto.CreateOrder;
import com.demo.entity.Order;

public interface OrderService {
    
	List<Order> getList();
	List<Order> getOrderByUser(String username);
	void placeOrder(CreateOrder createOrder);
}
