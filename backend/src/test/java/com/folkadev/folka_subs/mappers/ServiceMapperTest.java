package com.folkadev.folka_subs.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.domain.entities.Service;
import com.folkadev.folka_subs.domain.entities.Subscription;

import com.folkadev.folka_subs.mappers.impl.ServiceMapperImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceMapperTest {

  private final ServiceMapperImpl serviceMapper = new ServiceMapperImpl();

  @Nested
  @DisplayName("Service to DTO Mapping")
  class ServiceToDto {
    @Test
    void shouldMapServiceToDTOWhenPassedAllFields() {
      UUID serviceId = UUID.randomUUID();
      Service service = new Service();
      service.setId(serviceId);
      service.setName("spotify");
      service.setDisplayName("Spotify");
      service.setSubscriptions(new ArrayList<>());

      ServiceDto serviceDto = serviceMapper.toDto(service);

      assertEquals(service.getId(), serviceDto.id());
      assertEquals(service.getName(), serviceDto.name());
      assertEquals(service.getDisplayName(), serviceDto.displayName());
    }

    @Test
    void shouldMapServiceToDTOWhenPassedNullFields() {
      UUID serviceId = UUID.randomUUID();
      Service service = new Service();
      service.setId(serviceId);
      service.setName(null);
      service.setDisplayName(null);
      service.setSubscriptions(null);

      ServiceDto serviceDto = serviceMapper.toDto(service);

      assertEquals(service.getId(), serviceDto.id());
      assertNull(serviceDto.name());

      assertNull(serviceDto.displayName());
      assertNull(serviceDto.subscriptions());
    }

    @Test
    void shouldHandleNullService() {
      Service service = null;
      ServiceDto serviceDto = serviceMapper.toDto(service);
      assertNull(serviceDto);
    }
  }

  @Nested
  @DisplayName("Dto to Service Mapping")
  class DtoToService {
    @Test
    void shouldMapDtoToServiceWhenPassedAllFields() {
      UUID serviceDtoId = UUID.randomUUID();
      ServiceDto serviceDto = new ServiceDto(serviceDtoId, "spotify", "Spotify", new ArrayList<Subscription>());

      Service service = serviceMapper.fromDto(serviceDto);

      assertEquals(service.getName(), serviceDto.name());
      assertEquals(service.getDisplayName(), serviceDto.displayName());
      assertEquals(service.getSubscriptions(), serviceDto.subscriptions());
    }

    @Test
    void shouldMapDtoToServiceWhenPassedNullFields() {
      UUID serviceDtoId = UUID.randomUUID();
      ServiceDto serviceDto = new ServiceDto(serviceDtoId, null, null, null);

      Service service = serviceMapper.fromDto(serviceDto);

      assertNull(service.getName());
      assertNull(service.getDisplayName());
      assertEquals(service.getSubscriptions(), service.getSubscriptions());
    }

    @Test
    void shouldHandleNullDto() {
      ServiceDto serviceDto = null;
      Service service = serviceMapper.fromDto(serviceDto);
      assertNull(service);
    }
  }
}
