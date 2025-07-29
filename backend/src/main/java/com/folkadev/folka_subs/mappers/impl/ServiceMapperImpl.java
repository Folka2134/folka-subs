package com.folkadev.folka_subs.mappers.impl;

import org.springframework.stereotype.Component;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.domain.entities.Service;
import com.folkadev.folka_subs.mappers.ServiceMapper;

@Component
public class ServiceMapperImpl implements ServiceMapper {
  @Override
  public Service fromDto(ServiceDto serviceDto) {
    return new Service(serviceDto.name(), serviceDto.displayName());
  }

  @Override
  public ServiceDto toDto(Service service) {
    return new ServiceDto(service.getId(), service.getName(), service.getDisplayName());
  }
}
