package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.CreateTag;
import com.demo.entity.Tag;
import com.demo.response.MessageResponse;
import com.demo.service.TagService;

@RestController
@RequestMapping("/api/tag")
public class TagController {
   @Autowired
   private TagService tagService;
   
   @GetMapping("/")
   public ResponseEntity<List<Tag>> getTagList(){
	   List<Tag> tags = tagService.getListTag();
	   return ResponseEntity.ok(tags);
   }
   @PostMapping("/create")
   public ResponseEntity<Tag> createTag(@RequestBody CreateTag createTag){
	   Tag tag = tagService.createTag(createTag);
	   return ResponseEntity.ok(tag);
   }
   @PutMapping("/update/{id}")
   public ResponseEntity<Tag> updateTag(@PathVariable long id , @RequestBody CreateTag createTag){
	   Tag tag = tagService.updateTag(id, createTag);
	   return ResponseEntity.ok(tag);
   }
   @PutMapping("/enabled/{id}")
   public ResponseEntity<?> enabled(@PathVariable long id){
	   tagService.enableTag(id);
	   return ResponseEntity.ok(new MessageResponse("Enable tag success"));
   }
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteTag(@PathVariable long id){
	   tagService.deleteTag(id);
	   return ResponseEntity.ok(new MessageResponse("delete Sucess"));
   }
}
