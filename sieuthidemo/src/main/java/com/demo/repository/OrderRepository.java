package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	 @Query(value ="Select * from order where user_id = :id order by id desc",nativeQuery = true)
	    List<Order> getOrderByUser(long id);
}
