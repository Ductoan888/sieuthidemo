package com.demo.entity;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "blog")
public class Blog {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "description", columnDefinition = "TEXT")
   private String description;
   
   @Column(name = "content" , columnDefinition = "TEXT")
   private String content;
   
   private String title;
   
   private Timestamp createAt;
   
   @ManyToOne
   @JoinColumn(name = "image_id")
   private Image image;
   
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
   
   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "blog_tag",joinColumns = @JoinColumn(name ="blog_id"),inverseJoinColumns = @JoinColumn(name = "tag_id"))
   
   private Set<Tag>tags = new HashSet<>();
   
}
