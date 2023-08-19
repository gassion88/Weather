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
@IdClass(LocationId.class)
public class Location {
    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "country_code")
    private String countryCode;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(
            name="users_locations",
            joinColumns={@JoinColumn(name="LOCATION_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")})
    private List<User> users = new ArrayList<>();

    @Column(name = "latitude")
    private DecimalFormat latitude;

    @Column(name = "longitude")
    private DecimalFormat longitude;

    @Transient
    private boolean isSaved;

    public void addUserToLocation(User user) {
        this.users.add(user);
    }
}
