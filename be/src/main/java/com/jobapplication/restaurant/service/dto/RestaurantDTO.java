package com.jobapplication.restaurant.service.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantDTO {
  @NotNull
  private String name;
  @Nullable
  private String address;
  @Nullable
  private String cuisine;
  @Nullable
  private String description;
}
