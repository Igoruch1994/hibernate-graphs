package com.ihoruch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address", schema = "hibernate_graph")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserAddressGenerator")
    @SequenceGenerator(name = "UserAddressGenerator", sequenceName = "UserAddressSequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String street;

}
