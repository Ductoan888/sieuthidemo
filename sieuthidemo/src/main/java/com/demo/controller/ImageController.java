package com.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.Image;
import com.demo.exception.BadRequestException;
import com.demo.exception.InternalServerException;
import com.demo.service.ImageService;

@RestController
@RequestMapping("/api/image")
public class ImageController {
  private static final String UPLOAD_DIR = System.getProperty("user.dir")+ "/src/main/resources/static/photo/";
@Autowired
  private ImageService imageService;
	@GetMapping("/")
	public ResponseEntity<List<Image>> getImage(){
		List<Image> images = imageService.getListImage();
		return ResponseEntity.ok(images);
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getListByUser(@PathVariable long userId){
		List<Image> images = imageService.getListByUser(userId);
		return ResponseEntity.ok(images);
	}
	@PostMapping("/upload-file")
	public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile multipartFile){
		File uploadDir = new File(UPLOAD_DIR);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		String orginalFilename = multipartFile.getOriginalFilename();
		String extension = orginalFilename.substring(orginalFilename.lastIndexOf(".")+1);
		if (orginalFilename != null && orginalFilename.length()>0) {
			if (!extension.equals("png") && !extension.equals("jpg")) {
				throw new BadRequestException("khong hop le file");
			}
			try {
				Image img = new Image();
				img.setName(multipartFile.getName());
				img.setSize(multipartFile.getSize());
				img.setData(multipartFile.getBytes());
				img.setType(extension);
				String uid = UUID.randomUUID().toString();
				String link = UPLOAD_DIR + uid +" . " + extension;
				
				File serverFile = new File(link);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(multipartFile.getBytes());
				stream.close();
				imageService.save(img);
				return ResponseEntity.ok(img);
			} catch (Exception e) {
				throw new InternalServerException("Loi khong upload dc file");
			}
		}
		throw new BadRequestException("File khong hop le");
	}
}
