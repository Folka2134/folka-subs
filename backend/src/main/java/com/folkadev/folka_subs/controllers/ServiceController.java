package com.folkadev.folka_subs.controllers;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.services.ServiceService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

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
@RequestMapping(path = "/services")
public class ServiceController {

  private final ServiceService serviceService;

  public ServiceController(ServiceService serviceService) {
    this.serviceService = serviceService;
  }

  @GetMapping
  public List<ServiceDto> getAllServices() {
    return serviceService.getServices();
  }

  @GetMapping(path = "/{service_id}")
  public ResponseEntity<ServiceDto> getService(@PathVariable("service_id") UUID serviceId) {
    return serviceService.getService(serviceId).map(subscription -> ResponseEntity.ok(subscription))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ServiceDto> createService(@Valid @RequestBody ServiceDto serviceDto) {

    ServiceDto createdServiceDto = serviceService.createService(serviceDto);
    return new ResponseEntity<>(createdServiceDto, HttpStatus.CREATED);
  }

  @PutMapping(path = "/{service_id}")
  public ResponseEntity<ServiceDto> updateService(@PathVariable("service_id") UUID serviceId,
      @Valid @RequestBody ServiceDto serviceDto) {
    ServiceDto updatedService = serviceService.updateService(serviceId, serviceDto);
    return new ResponseEntity<>(updatedService, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{service_id}")
  public void deleteService(@PathVariable("service_id") UUID serviceId) {
    serviceService.deleteService(serviceId);
  }
}
