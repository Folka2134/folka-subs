package com.folkadev.folka_subs.mappers.impl;

import org.springframework.stereotype.Component;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.domain.entities.Service;
import com.folkadev.folka_subs.mappers.ServiceMapper;

@Component
public class ServiceMapperImpl implements ServiceMapper {
  @Override
  public ServiceDto toDto(Service service) {
    if (service == null) {
      return null;
    }
    return new ServiceDto(service.getId(), service.getName(), service.getDisplayName(), service.getSubscriptions());
  }

  @Override
  public Service fromDto(ServiceDto serviceDto) {
    if (serviceDto == null) {
      return null;
    }
    return Service.builder().name(serviceDto.name()).displayName(serviceDto.displayName()).build();
  }

}
