package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.CreateCategory;
import com.demo.entity.Category;
import com.demo.entity.User;
import com.demo.exception.NotFoundException;
import com.demo.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
   @Autowired
   private CategoryRepository categoryRepository;

@Override
public List<Category> findAll() {
	// TODO Auto-generated method stub
	List<Category> categories = categoryRepository.findAll();
	return categories;
}

@Override
public List<Category> getListEnabled() {
	// TODO Auto-generated method stub
	List<Category> list = categoryRepository.findAllByEnabled();
	
	return list;
}

@Override
public Category createCategory(CreateCategory createCategory) {
	// TODO Auto-generated method stub
	Category category = new Category();
	category.setName(createCategory.getName());
	category.setEnable(false);
	categoryRepository.save(category);
	return category;
}

@Override
public Category updateCategory(Long id, CreateCategory createCategory) {
	// TODO Auto-generated method stub
	Category category = categoryRepository.findById(id).orElseThrow(()-> new NotFoundException("not found category by id"+id));
	category.setName(createCategory.getName());
	categoryRepository.save(category);
	return category;
}

@Override
public void deleteCategory(long id) {
	// TODO Auto-generated method stub
	categoryRepository.deleteById(id);
	
}

@Override
public void enableCategory(long id) {
	// TODO Auto-generated method stub
	Category category =categoryRepository.findById(id).orElseThrow(()->new NotFoundException("not found id"+id));
	if (category.isEnable()) {
		category.setEnable(false);
		
	}else {
		category.setEnable(true);
	}
	categoryRepository.save(category);
	
}
   
}
