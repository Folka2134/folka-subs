package com.folkadev.folka_subs.mappers;

import com.folkadev.folka_subs.domain.entities.Service;
import com.folkadev.folka_subs.domain.dto.ServiceDto;

public interface ServiceMapper {
  Service fromDto(ServiceDto serviceDto);

  ServiceDto toDto(Service service);
}
