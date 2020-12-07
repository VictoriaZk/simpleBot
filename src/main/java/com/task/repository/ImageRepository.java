package com.task.repository;

import com.task.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ImageRepository.
 *
 * @author Viktoryia Zhak
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("SELECT i FROM Image i WHERE i.city.name = ?1")
    List<Image> findByCityName(String name);
}