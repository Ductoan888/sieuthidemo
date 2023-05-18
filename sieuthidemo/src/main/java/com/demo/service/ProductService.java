package com.demo.service;

import java.util.List;

import com.demo.dto.CreateProduct;
import com.demo.entity.Product;


public interface ProductService {
    List<Product> getAllProduct();
    List<Product> getProductByCategory(Long id);
    List<Product> searchProduct(String keyword);
    List<Product> getListByPrice();
    List<Product> getListByPriceRange(long id , int min , int max);
    List<Product> findRelatedProduct (long id);
    List<Product> getListNewst(int number);
    
    Product getProduct(long id);  
   Product updateProduct(long id ,CreateProduct createProduct);
   void deleteProduct(long id);
   Product createProduct(CreateProduct createProduct);
   
   
}
