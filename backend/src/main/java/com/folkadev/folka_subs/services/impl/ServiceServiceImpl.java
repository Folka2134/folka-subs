package com.folkadev.folka_subs.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.mappers.ServiceMapper;
import com.folkadev.folka_subs.repositories.ServiceRepository;
import com.folkadev.folka_subs.services.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService {

  private final ServiceRepository serviceRepository;
  private final ServiceMapper serviceMapper;

  public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
    this.serviceRepository = serviceRepository;
    this.serviceMapper = serviceMapper;
  }

  @Override
  public List<ServiceDto> getServices() {
    return serviceRepository.findAll().stream().map(serviceMapper::toDto).toList();
  }

  @Override
  public Optional<ServiceDto> getService(UUID serviceId) {
    return serviceRepository.findById(serviceId).map(serviceMapper::toDto);
  }

  @Override
  public ServiceDto createService(ServiceDto serviceDto) {
    // TODO: Implement createService
    return null;
  }

  @Override
  public ServiceDto updateService(UUID serviceId, ServiceDto serviceDto) {
    // TODO: Implement updateService
    return null;
  }

  @Override
  public void deleteService(UUID serviceId) {
    // TODO: Implement deleteService
  }
}
