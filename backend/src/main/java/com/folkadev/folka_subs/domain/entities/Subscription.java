package com.folkadev.folka_subs.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @ManyToOne()
  @JoinColumn(name = "service_id", nullable = false)
  private UUID serviceId;

  @Column(name = "price")
  private int price;

  @Column(name = "currency", nullable = false)
  private String currency;

  @Column(name = "trial_period_days")
  private int trialPeriodDays;

  @Column(name = "billing_cycle", nullable = false)
  private BillingCycle billingCycle;

  @Column(name = "notification_days_before")
  private List<Integer> notificationDaysBefore;

  @Column(name = "status", nullable = false)
  private Status status;

  @Column(name = "notes")
  private String notes;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "cancelled_date")
  private LocalDateTime cancelledDate;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public Subscription() {
  }

  public Subscription(UUID service_id, int price, String currency, int trialPeriodDays,
      BillingCycle billingCycle, List<Integer> notificationDaysBefore, Status status,
      String notes, LocalDateTime startDate, LocalDateTime cancelledDate) {
    this.serviceId = service_id;
    this.price = price;
    this.currency = currency;
    this.trialPeriodDays = trialPeriodDays;
    this.billingCycle = billingCycle;
    this.notificationDaysBefore = notificationDaysBefore;
    this.status = status;
    this.notes = notes;
    this.startDate = startDate;
    this.cancelledDate = cancelledDate;
  }

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public UUID getServiceId() {
    return serviceId;
  }

  public void setServiceId(UUID serviceId) {
    this.serviceId = serviceId;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public int getTrialPeriodDays() {
    return trialPeriodDays;
  }

  public void setTrialPeriodDays(int trialPeriodDays) {
    this.trialPeriodDays = trialPeriodDays;
  }

  public BillingCycle getBillingCycle() {
    return billingCycle;
  }

  public void setBillingCycle(BillingCycle billingCycle) {
    this.billingCycle = billingCycle;
  }

  public List<Integer> getNotificationDaysBefore() {
    return notificationDaysBefore;
  }

  public void setNotificationDaysBefore(List<Integer> notificationDaysBefore) {
    this.notificationDaysBefore = notificationDaysBefore;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getCancelledDate() {
    return cancelledDate;
  }

  public void setCancelledDate(LocalDateTime cancelledDate) {
    this.cancelledDate = cancelledDate;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Subscription that = (Subscription) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(serviceId, that.serviceId) &&
        Objects.equals(price, that.price) &&
        Objects.equals(currency, that.currency) &&
        trialPeriodDays == that.trialPeriodDays &&
        billingCycle == that.billingCycle &&
        Objects.equals(notificationDaysBefore, that.notificationDaysBefore) &&
        status == that.status &&
        Objects.equals(notes, that.notes) &&
        Objects.equals(startDate, that.startDate) &&
        Objects.equals(cancelledDate, that.cancelledDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, serviceId, price, currency, trialPeriodDays, billingCycle,
        notificationDaysBefore, status, notes, startDate, cancelledDate);
  }

  @Override
  public String toString() {
    return "Subscription{" +
        "id=" + id +
        ", serviceId=" + serviceId +
        ", price='" + price + '\'' +
        ", currency='" + currency + '\'' +
        ", trialPeriodDays=" + trialPeriodDays +
        ", billingCycle=" + billingCycle +
        ", notificationDaysBefore=" + notificationDaysBefore +
        ", status=" + status +
        ", notes='" + notes + '\'' +
        ", startDate=" + startDate +
        ", cancelledDate=" + cancelledDate +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
