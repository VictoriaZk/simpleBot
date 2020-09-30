package com.task.repository;

import com.task.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT c.description FROM City c WHERE c.name = ?1")
    Optional<String> findDescriptionByName(String name);

    @Modifying
    @Query("UPDATE City c SET c.description = ?2 WHERE c.id = ?1")
    void updateDescription(Long id, String newDescription);
}
