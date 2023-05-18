package com.demo.service;

import java.util.List;

import com.demo.entity.Image;

public interface ImageService {
 
	List<Image> getListImage();
	Image getImageById(long id);
	List<Image> getListByUser (long userId);
	void deleteImage(long id);
	Image save(Image image);
}
