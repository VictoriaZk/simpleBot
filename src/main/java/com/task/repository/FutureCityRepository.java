package com.task.repository;

import com.task.model.FutureCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * FutureCityRepository.
 *
 * @author Viktoryia Zhak
 */
public interface FutureCityRepository extends JpaRepository<FutureCity, Long> {
    Optional<FutureCity> findByName(String name);
}
