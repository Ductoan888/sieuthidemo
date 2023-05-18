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
import org.springframework.web.service.annotation.GetExchange;

import com.demo.dto.CreateProduct;
import com.demo.entity.Product;
import com.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct(){
    	List<Product> list = productService.getAllProduct();
    	return ResponseEntity.ok(list);
    	
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestBody CreateProduct createProduct){
    	Product product = productService.updateProduct(id, createProduct);
    	return ResponseEntity.ok(product);
    	
    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProduct createProduct){
    	Product product = productService.createProduct(createProduct);
    	return ResponseEntity.ok(product);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
    	productService.deleteProduct(id);
    	return ResponseEntity.ok("delelte success");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable long id){
    	List<Product> list = productService.getProductByCategory(id);
    	return ResponseEntity.ok(list);
    }
    @GetMapping("/related/{id}")
    public ResponseEntity<List<Product>> getRelatedProduct(@PathVariable long id){
    	List<Product> list = productService.findRelatedProduct(id);
    	return ResponseEntity.ok(list);
    }
    @GetMapping("/price")
    public ResponseEntity<List<Product>> getListProductByPrice(){
    	List<Product> list = productService.getListByPrice();
    	return ResponseEntity.ok(list);
    	
    }
    @GetMapping("/range")
    public ResponseEntity<List<Product>> getListByPriceRange(@PathVariable long id , @PathVariable int min, @PathVariable int max){
    	List<Product> list = productService.getListByPriceRange(id, min, max);
    	return ResponseEntity.ok(list);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam("keyword") String keyword){
    	List<Product> list = productService.searchProduct(keyword);
    	return ResponseEntity.ok(list);
    }
    @GetMapping("/newest/{number}")
    public ResponseEntity<List<Product>> getnewestbynumber(@PathVariable int number){
    	List<Product> products = productService.getListNewst(number);
    	return ResponseEntity.ok(products);
    }
}

