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
@Table(name = "USER_TBL", schema = "PUBLIC", catalog = "TESTDB")
public class User {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "USER_ID", nullable = false)
  private long userId;

  @Basic
  @Column(name = "USERNAME", nullable = false, length = 200)
  private Object username;
}
