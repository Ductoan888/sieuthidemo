 package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.CreateTag;
import com.demo.entity.Tag;
import com.demo.exception.NotFoundException;
import com.demo.repository.TagRepository;
@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	private TagRepository tagRepository;

	@Override
	public List<Tag> getListTag() {
		// TODO Auto-generated method stub
		List<Tag> tags = tagRepository.findAll();
		return tags;
	}

	@Override
	public Tag createTag(CreateTag createTag) {
		// TODO Auto-generated method stub
		Tag tag = new Tag();
		tag.setName(createTag.getName());
		tag.setEnable(false);
		tagRepository.save(tag);
		return tag;
	}

	@Override
	public Tag updateTag(long id, CreateTag createTag) {
		// TODO Auto-generated method stub
		Tag tag = tagRepository.findById(id).orElseThrow(()-> new NotFoundException("not found Tag"));
		tag.setName(createTag.getName());
		return tag;
	}

	@Override
	public void enableTag(Long id) {
		// TODO Auto-generated method stub
		Tag tag = tagRepository.findById(id).get();
		if (tag.isEnable()) {
			tag.setEnable(false);
		}else {
			tag.setEnable(true);
		}
		tagRepository.save(tag);
		
		
	}

	@Override
	public void deleteTag(long id) {
		// TODO Auto-generated method stub
		Tag tag = tagRepository.findById(id).orElseThrow(()-> new NotFoundException("not found Tag"));
		tagRepository.delete(tag);
		
	}
	
	

}
