package com.jobapplication.restaurant.repository.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SessionRestaurantPK implements Serializable {
  private String sessionId;
  private long restaurantId;
}
