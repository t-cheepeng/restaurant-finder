package com.jobapplication.restaurant.repository.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SPRING_SESSION", schema = "PUBLIC", catalog = "TESTDB")
public class SpringSession {
  @Id
  @Column(name = "PRIMARY_ID", nullable = false, length = 36)
  private String primaryId;

  @Basic
  @Column(name = "SESSION_ID", nullable = false, length = 36)
  private String sessionId;

  @Basic
  @Column(name = "CREATION_TIME", nullable = false)
  private long creationTime;

  @Basic
  @Column(name = "LAST_ACCESS_TIME", nullable = false)
  private long lastAccessTime;

  @Basic
  @Column(name = "MAX_INACTIVE_INTERVAL", nullable = false)
  private int maxInactiveInterval;

  @Basic
  @Column(name = "EXPIRY_TIME", nullable = false)
  private long expiryTime;

  @Basic
  @Column(name = "PRINCIPAL_NAME", nullable = true, length = 100)
  private Object principalName;
}
