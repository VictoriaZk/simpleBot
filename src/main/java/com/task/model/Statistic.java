package com.task.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "STATISTIC")
public class Statistic {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer amount;

    @Column(name = "quarantine")
    private Boolean isQuarantineNeeded;

    @OneToOne
    @JoinColumn(name = "countryId")
    private Country country;
}
