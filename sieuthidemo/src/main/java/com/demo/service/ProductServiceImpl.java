package com.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.CreateProduct;

import com.demo.entity.Category;
import com.demo.entity.Image;
import com.demo.entity.Product;
import com.demo.exception.NotFoundException;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ImageRepository;
import com.demo.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	@Autowired 
	private CategoryRepository categoryRepository;
	@Autowired
	private ImageRepository imageRepository;

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findAll();
		return products;
	}

	@Override
	public List<Product> getProductByCategory(Long id) {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.getListProductByCategory(id);
		
		return products;
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.searchProduct(keyword);
		
		return products;
	}

	@Override
	public List<Product> getListByPrice() {
		// TODO Auto-generated method stub
		List<Product>list = productRepository.getListByPrice();
		
		return list;
	}

	@Override
	public List<Product> getListByPriceRange(long id, int min, int max) {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.getListProductByPriceRange(id, min, max);
		
		return products;
	}

	@Override
	public List<Product> findRelatedProduct(long id) {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findRelatedProduct(id);
		
		return products;
	}

	@Override
	public List<Product> getListNewst(int number) {
		// TODO Auto-generated method stub
		List<Product> list = productRepository.getListNewest(number);
		return list;
	}

	@Override
	public Product getProduct(long id) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id).get();
		return product;
	}

	@Override
	public Product updateProduct(long id, CreateProduct createProduct) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id).get();
		product.setName(createProduct.getName());
		product.setDescription(createProduct.getDescription());
		Category category = categoryRepository.findById(createProduct.getCategoryId()).orElseThrow(()-> new NotFoundException("not found category"));
		product.setCategory(category);
		product.setQuantity(createProduct.getQuantity());
		
		Set<Image> images = new HashSet<>();
		for(long imageId : createProduct.getImageIds()) {
			Image image = imageRepository.findById(imageId).get();
			images.add(image);
		}
		
		product.setImages(images);
		productRepository.save(product);
		
		return product;
	}

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id).get();
		productRepository.delete(product);
	}

	@Override
	public Product createProduct(CreateProduct createProduct) {
		// TODO Auto-generated method stub
		Product product = new Product();
		 product.setName(createProduct.getName());
	        product.setDescription(createProduct.getDescription());
	        product.setPrice(createProduct.getPrice());
	        product.setQuantity(createProduct.getQuantity());
	        Category category = categoryRepository.findById(createProduct.getCategoryId()).orElseThrow(()-> new NotFoundException("Not Found Category With Id: " + createProduct.getCategoryId()));
	        product.setCategory(category);

	        Set<Image> images = new HashSet<>();
	        for(long imageId: createProduct.getImageIds()){
	            Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("Not Found Image With Id: " + imageId));
	            images.add(image);
	        }
	        product.setImages(images);
	        productRepository.save(product);
	        return product;
		
	}

}
