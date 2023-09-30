package com.jobapplication.restaurant.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateRestaurantRequest {

  @NotBlank(message = "Name of restaurant is required.")
  @Size(max = 200)
  private String name;

  @Size(max = 200)
  private String address;

  @Size(max = 100)
  private String cuisine;

  @Size(max = 10000)
  private String description;
}
