package com.demo.service;

import java.util.List;

import com.demo.dto.CreateBlog;
import com.demo.entity.Blog;

public interface BlogService {
     
	List<Blog> getList();
	List<Blog> getListNew(int limit);
	
	Blog getBlog(Long id);
	Blog createBlog(CreateBlog createBlog);
	
	Blog updateBlog(Long id , CreateBlog createBlog);
	void deleteBlog(Long id);
}
