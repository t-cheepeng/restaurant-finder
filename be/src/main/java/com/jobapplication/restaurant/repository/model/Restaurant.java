package com.jobapplication.restaurant.repository.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "RESTAURANT_TBL", schema = "PUBLIC", catalog = "TESTDB")
public class Restaurant {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "RESTAURANT_ID", nullable = false)
  private long restaurantId;

  @Basic
  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  @Basic
  @Column(name = "ADDRESS", nullable = true, length = 200)
  private String address;

  @Basic
  @Column(name = "CUISINE", nullable = true, length = 100)
  private String cuisine;

  @Basic
  @Column(name = "DESCRIPTION", nullable = true, length = 10000)
  private String description;
}
