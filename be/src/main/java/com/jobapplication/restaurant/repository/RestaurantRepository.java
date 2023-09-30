package com.jobapplication.restaurant.repository;

import com.jobapplication.restaurant.repository.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {}
