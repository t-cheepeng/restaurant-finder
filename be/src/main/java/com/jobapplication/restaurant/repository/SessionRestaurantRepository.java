package com.jobapplication.restaurant.repository;

import com.jobapplication.restaurant.repository.model.Restaurant;
import com.jobapplication.restaurant.repository.model.SessionRestaurant;
import com.jobapplication.restaurant.repository.model.SessionRestaurantPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SessionRestaurantRepository
    extends JpaRepository<SessionRestaurant, SessionRestaurantPK> {

  @Query(
      "SELECT r FROM SessionRestaurant ur JOIN Restaurant r ON ur.sessionRestaurantPK.restaurantId = r.restaurantId "
          + "WHERE ur.sessionRestaurantPK.sessionId = :sessionId")
  List<Restaurant> findAllRestaurantsBySession(String sessionId);
}
