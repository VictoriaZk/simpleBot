package com.task.repository;

import com.task.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    @Modifying
    @Query("UPDATE Statistic s SET s.amount = ?2, s.day = ?3, s.isQuarantineNeeded = ?4 WHERE s.id = ?1")
    void updateStatistic(Long id, Long amount, Integer day, Boolean quarantine);

    @Query("SELECT s FROM Statistic s WHERE s.country.id = ?1")
    Optional<Statistic> findByCountryId(Long id);
}
