package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(nativeQuery = true, value = "Select * FROM image Where uploaded_by = ?1")
    List<Image> getListImageOfUser(Long userId);
}
