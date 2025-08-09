package com.folkadev.folka_subs.services;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.domain.entities.Service;
import com.folkadev.folka_subs.mappers.ServiceMapper;
import com.folkadev.folka_subs.repositories.ServiceRepository;
import com.folkadev.folka_subs.services.impl.ServiceServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceServiceTest {

  @Mock
  private ServiceMapper serviceMapper;

  @Mock
  private ServiceRepository serviceRepository;

  @InjectMocks
  private ServiceServiceImpl serviceService;

  @Nested
  @DisplayName("GetServices returns a list of serviceDtos or an empty list")
  class GetServices {
    @Test
    @DisplayName("Should return a list of service DTOs when services exist")
    void returnsListOfServiceDtos_whenServicesExist() {
      UUID serviceId1 = UUID.randomUUID();
      Service service1 = new Service(serviceId1, "spotify", "Spotify", new ArrayList<>(), LocalDateTime.now(),
          LocalDateTime.now());
      ServiceDto serviceDto1 = new ServiceDto(serviceId1, "spotify", "Spotify", new ArrayList<>());

      UUID serviceId2 = UUID.randomUUID();
      Service service2 = new Service(serviceId2, "youtube", "Youtube", new ArrayList<>(), LocalDateTime.now(),
          LocalDateTime.now());
      ServiceDto serviceDto2 = new ServiceDto(serviceId2, "youtube", "Youtube", new ArrayList<>());

      List<Service> mockServices = Arrays.asList(service1, service2);

      when(serviceRepository.findAll()).thenReturn(mockServices);
      when(serviceMapper.toDto(service1)).thenReturn(serviceDto1);
      when(serviceMapper.toDto(service2)).thenReturn(serviceDto2);

      List<ServiceDto> result = serviceService.getServices();

      assertNotNull(result);
      assertFalse(result.isEmpty());
      assertEquals(2, result.size());
      assertEquals("spotify", result.get(0).name());
      assertEquals("youtube", result.get(1).name());
    }

    @Test
    @DisplayName("Should return an empty list when services don't exist")
    void returnEmptyList_whenServicesDoNotExit() {
      List<Service> services = new ArrayList<>();

      when(serviceRepository.findAll()).thenReturn(services);

      List<ServiceDto> result = serviceService.getServices();

      assertNotNull(result);
      assertTrue(result.isEmpty());
    }
  }

  @Nested
  @DisplayName("GetService should return a specified service, null or invalid argument exception")
  class GetService {
    @Test
    @DisplayName("Should return a specified service dto when service exists")
    void returnServiceWhenProvidedServiceId() {
      UUID serviceId = UUID.randomUUID();
      Service service = new Service(serviceId, "spotify", "Spotify", new ArrayList<>(), LocalDateTime.now(),
          LocalDateTime.now());
      ServiceDto serviceDto = new ServiceDto(serviceId, "spotify", "Spotify", new ArrayList<>());

      when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));
      when(serviceMapper.toDto(service)).thenReturn(serviceDto);

      Optional<ServiceDto> result = serviceService.getService(serviceId);

      assertNotNull(result);
      assertFalse(result.isEmpty());
      assertEquals(Optional.of(serviceDto), result);
      assertEquals("spotify", result.get().name());
    }

    @Test
    @DisplayName("Should return empty Optional if service doesn't exist")
    void returnEmptyOptionalWhenServiceDoesNotExist() {
      UUID serviceId = UUID.randomUUID();

      when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());

      Optional<ServiceDto> result = serviceService.getService(serviceId);

      assertNotNull(result);
      assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should throw an exception when provided id is null")
    void throwExceptionWhenIdIsNull() {

      when(serviceRepository.findById(null)).thenThrow(new IllegalArgumentException("ID cannot be null"));

      assertThrows(IllegalArgumentException.class, () -> {
        serviceService.getService(null);
      });
    }
  }
}
