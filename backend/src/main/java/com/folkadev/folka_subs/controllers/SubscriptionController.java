package com.folkadev.folka_subs.controllers;

import java.util.List;
import java.util.UUID;

import com.folkadev.folka_subs.domain.dto.SubscriptionDto;
import com.folkadev.folka_subs.services.SubscriptionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

  public final SubscriptionService subscriptionService;

  public SubscriptionController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }

  @GetMapping
  public List<SubscriptionDto> getAllSubscriptions() {
    // TODO: Fix return
    return subscriptionService.getAllSubscriptions();
  }

  @GetMapping(path = "service_id")
  public List<SubscriptionDto> getSubscriptionsByServiceId(@PathVariable("service_id") UUID serviceId) {
    // TODO: Fix return
    return subscriptionService.getSubscriptionsByServiceId(serviceId);
  }

  @GetMapping(path = "subscription_id")
  public SubscriptionDto getSubscription(@PathVariable("subscription_id") UUID subscriptionId) {
    return subscriptionService.getSubscription(subscriptionId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<SubscriptionDto> createSubscription(SubscriptionDto subscriptionDto) {

    SubscriptionDto createdSubscription = subscriptionService.createSubscription(subscriptionDto);
    return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
  }

  @PutMapping(path = "subscription_id")
  public SubscriptionDto updateSubscription(@PathVariable("subscription_id") UUID subscriptionId,
      @RequestBody SubscriptionDto subscriptionDto) {
    return subscriptionService.updateSubscription(subscriptionId, subscriptionDto);
  }

  @DeleteMapping(path = "subscription_id")
  public void deleteSubscription(@PathVariable("subscription_id") UUID subscriptionId) {
    subscriptionService.deleteSubscription(subscriptionId);
  }

}
