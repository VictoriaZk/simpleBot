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
    @Query("UPDATE City c SET c.description = ?2, c.name = ?3, " +
            "c.recommendToVisit = ?4, c.notRecommendToVisit = ?5 WHERE c.id = ?1")
    void updateCty(Long id, String newDescription, String name, String recommend, String notRecommend);

    Optional<City> findByName(String name);
}
