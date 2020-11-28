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

    private Long amount;

    private Integer day;

    @Column(name = "quarantine")
    private Boolean isQuarantineNeeded;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
}
