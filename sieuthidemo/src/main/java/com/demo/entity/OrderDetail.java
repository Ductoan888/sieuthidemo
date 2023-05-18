package com.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private long price;
	
	private int quantity;
	
	private long subTotal;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
