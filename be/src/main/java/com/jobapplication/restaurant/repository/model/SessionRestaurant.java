package com.jobapplication.restaurant.repository.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SESSION_RESTAURANT_TBL", schema = "PUBLIC", catalog = "TESTDB")
public class SessionRestaurant {
  @EmbeddedId private SessionRestaurantPK sessionRestaurantPK;
}
