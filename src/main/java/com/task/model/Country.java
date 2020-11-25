package com.task.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "COUNTRY")
public class Country {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Set<City> city;

    @OneToOne
    @JoinColumn(name = "statasticId")
    private Statistic statistic;
}
