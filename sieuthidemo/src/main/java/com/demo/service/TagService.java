package com.demo.service;

import java.util.List;

import com.demo.dto.CreateTag;
import com.demo.entity.Tag;

public interface TagService {
 
	List<Tag> getListTag();
	 
	Tag createTag(CreateTag  createTag);
	Tag updateTag(long id, CreateTag createTag);
	void enableTag(Long id);
	void deleteTag(long id);
}
