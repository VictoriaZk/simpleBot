package com.task.repository;

import com.task.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    @Modifying
    @Query("UPDATE Statistic s SET s.amount = ?2, s.isQuarantineNeeded = ?3 WHERE s.id = ?1")
    void updateStatistic(Long id, Integer amount, Boolean quarantine);
}
