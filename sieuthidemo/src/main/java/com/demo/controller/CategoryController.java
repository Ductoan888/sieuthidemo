package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.CreateCategory;
import com.demo.entity.Category;
import com.demo.response.MessageResponse;
import com.demo.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
   @Autowired
   private CategoryService  categoryService;
   
   @GetMapping("/")
   public ResponseEntity<List<Category>> getListcategory(){
	   List<Category> categories = categoryService.findAll();
	   return ResponseEntity.ok(categories);
   }
   @GetMapping("/enabled")
   public ResponseEntity<List<Category>> getListEnable(){
	   List<Category> list = categoryService.getListEnabled();
	   return ResponseEntity.ok(list);
	   
   }
   @PostMapping("/create")
   public ResponseEntity<Category> createCategory(@RequestBody CreateCategory createCategory){
	   Category category = categoryService.createCategory(createCategory);
	   return ResponseEntity.ok(category);
   }
   @PutMapping("/update/{id}")
   public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id ,@RequestBody CreateCategory createCategory ){
	   Category category = categoryService.updateCategory(id, createCategory);
	   return ResponseEntity.ok(category);
   }
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteCategory(@PathVariable Long id){
	   categoryService.deleteCategory(id);
	   return ResponseEntity.ok(new MessageResponse("delete is sucess"));
	   
   }
   @PutMapping("/enable/{id}")
   public ResponseEntity<?> enabled(@PathVariable Long id){
	   categoryService.enableCategory(id);
	   return ResponseEntity.ok(new MessageResponse("cap nhat ok"));
   }
}
