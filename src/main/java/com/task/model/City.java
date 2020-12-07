package com.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CITY")
public class City {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "recommend", columnDefinition = "text")
    private String recommendToVisit;

    @Column(name = "not_recommend", columnDefinition = "text")
    private String notRecommendToVisit;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "countryId")
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Hotel> hotels;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Image> images;
}
