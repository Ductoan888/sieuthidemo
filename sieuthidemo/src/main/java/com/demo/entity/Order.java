package com.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String firstname;
   private String lastname;
   private String country;
   private String address;
   private String town;
   private String state;
   private long postCode;
   private String email;
   private String phone;
   private String note;
   private long totalPrice;
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
   
   @OneToMany(mappedBy = "order")
   @JsonBackReference
   private Set<OrderDetail> orderDetails;
}
