package com.task.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "HOTEL")
public class Hotel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String cost;

    @Column(name = "stars")
    private Integer amountOfStars;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cityId")
    private City city;

    private Double rating;
}
