package com.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CITY")
public class City {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITY_ID_SEQ")
    @SequenceGenerator(name = "CITY_ID_SEQ", sequenceName = "CITY_ID_SEQ")
    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;
}
