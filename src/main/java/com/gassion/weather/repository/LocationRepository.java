package com.gassion.weather.repository;

import com.gassion.weather.entity.Location;
import com.gassion.weather.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByNameAndCountryCodeAndUserId(String name, String countryConde, Long userId);
    Optional<List<Location>> findAllByUser(User user);
    Optional<Location> findById(Integer id);
}
