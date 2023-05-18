package com.demo.service;

import java.util.List;

import com.demo.dto.CreateCategory;
import com.demo.entity.Category;

public interface CategoryService {
   List<Category> findAll();
   List<Category> getListEnabled();
   Category createCategory(CreateCategory createCategory);
   Category updateCategory( Long id ,CreateCategory createCategory);
   void deleteCategory(long id);
   void enableCategory(long id);
}
