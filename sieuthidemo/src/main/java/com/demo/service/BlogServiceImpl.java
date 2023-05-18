package com.demo.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.CreateBlog;
import com.demo.entity.Blog;
import com.demo.entity.Image;
import com.demo.entity.Tag;
import com.demo.entity.User;
import com.demo.exception.NotFoundException;
import com.demo.repository.BlogRepository;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ImageRepository;
import com.demo.repository.TagRepository;
import com.demo.repository.UserRepository;
@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private UserRepository  userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private ImageRepository imageRepository;
	@Override
	public List<Blog> getList() {
		// TODO Auto-generated method stub
		List<Blog> blogs = blogRepository.findAll();
		return blogs;
	}

	@Override
	public List<Blog> getListNew(int limit) {
		// TODO Auto-generated method stub
		List<Blog> list = blogRepository.getListNew(limit);
		
		return list;
	}

	@Override
	public Blog getBlog(Long id) {
		// TODO Auto-generated method stub
		Blog blog = blogRepository.findById(id).orElseThrow(()-> new NotFoundException("Blog is not find"));
		
		return blog;
	}

	@Override
	public Blog createBlog(CreateBlog createBlog) {
		// TODO Auto-generated method stub
		Blog blog = new Blog();
		blog.setContent(createBlog.getContent());
		blog.setDescription(createBlog.getDescription());
		blog.setTitle(createBlog.getTitle());
		User user = userRepository.findByUsername(createBlog.getUsername()).orElseThrow(()-> new NotFoundException("Not found User"));
		blog.setUser(user);
	    blog.setCreateAt(new Timestamp(System.currentTimeMillis()));
	   Image image = imageRepository.findById(createBlog.getImageId()).orElseThrow(()->new NotFoundException("not found image"));
	   blog.setImage(image);
		Set<Tag> tags = new HashSet<>();
		for(Long tagId : createBlog.getTags()) {
			Tag tag = tagRepository.findById(tagId).orElseThrow(()->new NotFoundException("not found tag"));
			tags.add(tag);
		}
		blog.setTags(tags);
		blogRepository.save(blog);
		
		return blog;
	}

	@Override
	public Blog updateBlog(Long id, CreateBlog createBlog) {
		// TODO Auto-generated method stub
		Blog blog = blogRepository.findById(id).get();
        blog.setContent(createBlog.getContent());
        blog.setDescription(createBlog.getDescription());
        blog.setTitle(createBlog.getTitle());
        Image image = imageRepository.findById(id).orElseThrow(()-> new NotFoundException("image not found"));
        blog.setImage(image);
        Set<Tag> tags = new HashSet<>();
        for(long tagId : createBlog.getTags()) {
        	Tag tag = tagRepository.findById(id).get();
        	tags.add(tag);
        }
        blog.setTags(tags);
        blogRepository.save(blog);
		return blog;
	}

	@Override
	public void deleteBlog(Long id) {
		// TODO Auto-generated method stub
		Blog blog = blogRepository.findById(id).orElseThrow(()-> new NotFoundException("Blog not found"));
		blogRepository.delete(blog);
		blog.getTags().remove(blog);
		
	}
	
	

}
