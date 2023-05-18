package com.demo.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.CreateOrder;
import com.demo.dto.CreateOrderDetailRequest;
import com.demo.entity.Order;
import com.demo.entity.OrderDetail;
import com.demo.entity.User;
import com.demo.exception.NotFoundException;
import com.demo.repository.OrderDetailRepository;
import com.demo.repository.OrderRepository;
import com.demo.repository.UserRepository;
@Service
public class OrderServiceImpl  implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
	@Override
	public List<Order> getList() {
		// TODO Auto-generated method stub
		List<Order> list = orderRepository.findAll();
		return list;
	}

	@Override
	public List<Order> getOrderByUser(String username) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username).orElseThrow(()-> new NotFoundException("not found user"));
		List<Order> orders = orderRepository.getOrderByUser(user.getId());
		return orders;
	}

	@Override
	public void placeOrder(CreateOrder createOrder) {
		// TODO Auto-generated method stub
		Order order = new Order();
		User user = userRepository.findByUsername(createOrder.getUsername()).get();
	    order.setAddress(createOrder.getAddress());
	    order.setCountry(createOrder.getCountry());
	    order.setFirstname(createOrder.getFirstname());
	    order.setEmail(createOrder.getEmail());
	    order.setNote(createOrder.getNote());
	    order.setState(createOrder.getState());
	    order.setPhone(createOrder.getPhone());
	    order.setTown(createOrder.getTown());
	    order.setPostCode(createOrder.getPostCode());
	    order.setLastname(createOrder.getLastname());
	    orderRepository.save(order);
	    long totalPrice = 0;
	    for(CreateOrderDetailRequest createOrderDetailRequest : createOrder.getCreateOrderDetailRequests()) {
	    	OrderDetail detail = new OrderDetail();
	    	detail.setName(createOrderDetailRequest.getName());
	    	detail.setPrice(createOrderDetailRequest.getPrice());
	    	detail.setQuantity(createOrderDetailRequest.getQuantity());
	    	detail.setSubTotal(createOrderDetailRequest.getPrice() * createOrderDetailRequest.getQuantity());
	    	detail.setOrder(order);
	    	totalPrice += detail.getSubTotal();
	    	orderDetailRepository.save(detail);
	    	
	    }
		order.setTotalPrice(totalPrice);
		order.setUser(user);
		orderRepository.save(order);
		
	}
    
}
