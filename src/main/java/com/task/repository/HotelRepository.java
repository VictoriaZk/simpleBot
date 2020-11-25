package com.task.repository;

import com.task.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Modifying
    @Query("UPDATE Hotel c SET c.name = ?2, c.amountOfStars = ?3 WHERE c.id = ?1")
    void updateNameAndStars(Long id, String name, Integer stars);
}
