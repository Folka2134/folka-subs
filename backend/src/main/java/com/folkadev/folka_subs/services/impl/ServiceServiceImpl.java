package com.folkadev.folka_subs.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.domain.entities.Service;
import com.folkadev.folka_subs.exceptions.ResourceAlreadyExistsException;
import com.folkadev.folka_subs.exceptions.ResourceNotFoundException;
import com.folkadev.folka_subs.mappers.ServiceMapper;
import com.folkadev.folka_subs.repositories.ServiceRepository;
import com.folkadev.folka_subs.services.ServiceService;

@org.springframework.stereotype.Service
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
    if (serviceDto.name() == null || serviceDto.name().isEmpty()) {
      throw new IllegalArgumentException("Service name is required");
    }

    if (serviceDto.displayName() == null || serviceDto.displayName().isEmpty()) {
      throw new IllegalArgumentException("Service display name is required");
    }

    if (serviceRepository.findByName(serviceDto.name()).isPresent()) {
      throw new ResourceAlreadyExistsException("Service already exists");
    }

    Service newService = serviceRepository.save(serviceMapper.fromDto(serviceDto));
    return serviceMapper.toDto(newService);
  }

  @Override
  public ServiceDto updateService(UUID serviceId, ServiceDto serviceDto) {
    Service serviceToUpdate = serviceRepository.findById(serviceId).orElseThrow(() -> {
      throw new ResourceNotFoundException("Service doesn't exist");
    });

    if (serviceDto.name() == serviceToUpdate.getName() && serviceDto.displayName() == serviceToUpdate.getDisplayName()
        || serviceDto.name() == null
        || serviceDto.name().isBlank()
        || serviceDto.displayName() == null
        || serviceDto.displayName().isBlank()) {
      throw new IllegalArgumentException("No new values detected");
    }

    serviceToUpdate.setName(serviceDto.name());
    serviceToUpdate.setDisplayName(serviceDto.displayName());

    Service updatedService = serviceRepository.save(serviceToUpdate);

    return serviceMapper.toDto(updatedService);
  }

  @Override
  public void deleteService(UUID serviceId) {
    Service serviceToDelete = serviceRepository.findById(serviceId).orElseThrow(() -> {
      throw new ResourceNotFoundException("Service doesn't exist");
    });
    serviceRepository.delete(serviceToDelete);
  }
}
