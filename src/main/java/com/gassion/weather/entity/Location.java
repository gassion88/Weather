package com.gassion.weather.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    private User user;

    @Column(name = "latitude")
    private DecimalFormat latitude;

    @Column(name = "longitude")
    private DecimalFormat longitude;
}
