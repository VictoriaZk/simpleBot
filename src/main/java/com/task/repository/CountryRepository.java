package com.task.repository;

import com.task.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Modifying
    @Query("UPDATE Country c SET c.name = ?2 WHERE c.id = ?1")
    void updateName(Long id, String newName);

    Optional<Country> findByName(String name);
}
