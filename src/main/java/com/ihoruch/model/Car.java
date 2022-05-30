package com.ihoruch.model;

import com.ihoruch.enums.Colour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car", schema = "hibernate_graph")
public class Car extends AbstractLazyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carGenerator")
    @SequenceGenerator(name = "carGenerator", sequenceName = "carSequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String model;

    @Enumerated(EnumType.STRING)
    private Colour colour;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ToString.Exclude
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<UserCar> userCars = new HashSet<>();

}
