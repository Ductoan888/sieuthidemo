package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.CreateOrder;
import com.demo.entity.Order;
import com.demo.response.MessageResponse;
import com.demo.service.OrderDetailService;
import com.demo.service.OrderService;
import com.demo.service.UserService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private UserService userService;
  @GetMapping("/")
  public ResponseEntity<List<Order>> getListOrder(){
	  List<Order> orders = orderService.getList();
	  return ResponseEntity.ok(orders);
	  
  }
  @GetMapping("/user")
  public ResponseEntity<List<Order>> getListOrderByUser(@RequestParam("username")String username){
	   List<Order> orders = orderService.getOrderByUser(username);
	   return new ResponseEntity<List<Order>>(orders , HttpStatus.OK);
  }
  @PostMapping("/create")
  public ResponseEntity<?> placeOrder(@RequestBody CreateOrder createOrder){
	  orderService.placeOrder(createOrder);
	  return ResponseEntity.ok(new MessageResponse("Order success"));
  }
}
