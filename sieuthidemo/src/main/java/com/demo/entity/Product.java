package com.demo.entity;

import java.util.HashSet;
import java.util.Set;

import com.demo.entity.Category;
import com.demo.entity.Image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  private String name;
  
  @Column(name = "description" , columnDefinition = "TEXT")
  private String description;
  private long price;
  private int quantity;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  @ManyToMany
  @JoinTable(name = "product_image" , joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "images_id"))
  private Set<Image> images = new HashSet<>();
}
