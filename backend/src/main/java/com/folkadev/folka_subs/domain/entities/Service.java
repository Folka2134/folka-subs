package com.folkadev.folka_subs.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
public class Service {

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false, updatable = false)
  @Builder.Default
  private UUID id = null;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "display_name", nullable = false)
  private String displayName;

  @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  private List<Subscription> subscriptions = new ArrayList<>();

  @CreationTimestamp
  @Column(name = "created_at")
  @Builder.Default
  private LocalDateTime createdAt = null;

  @UpdateTimestamp
  @Column(name = "updated_at")
  @Builder.Default
  private LocalDateTime updatedAt = null;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Service that = (Service) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(name, that.name) &&
        Objects.equals(displayName, that.displayName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, displayName);
  }

  @Override
  public String toString() {
    return "Service{" +
        "id=" + id +
        ", name='" + name + "'" +
        ", displayName='" + displayName + "'" +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
