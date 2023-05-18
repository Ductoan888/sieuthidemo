package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Image;
import com.demo.exception.NotFoundException;
import com.demo.repository.ImageRepository;

@Service
public class ImageServiceImpl  implements ImageService{
   @Autowired
   private ImageRepository imageRepository;

@Override
public List<Image> getListImage() {
	// TODO Auto-generated method stub
	List<Image> images = imageRepository.findAll();
	return images;
}

@Override
public Image getImageById(long id) {
	// TODO Auto-generated method stub
	Image image = imageRepository.findById(id).orElseThrow(()-> new NotFoundException("not found image"));
	
	return image;
}

@Override
public List<Image> getListByUser(long userId) {
	// TODO Auto-generated method stub
	List<Image> image = imageRepository.getListImageOfUser(userId);
	
	return image;
}

@Override
public void deleteImage(long id) {
	// TODO Auto-generated method stub
	Image image = imageRepository.findById(id).get();
	imageRepository.delete(image);
	
}

@Override
public Image save(Image image) {
	// TODO Auto-generated method stub
	
	return imageRepository.save(image);
}
}
