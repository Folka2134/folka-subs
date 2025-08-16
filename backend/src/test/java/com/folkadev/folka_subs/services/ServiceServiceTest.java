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
import com.folkadev.folka_subs.exceptions.ResourceAlreadyExistsException;
import com.folkadev.folka_subs.exceptions.ResourceNotFoundException;
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

  @Nested
  @DisplayName("Create service should create a service or throw an exception")
  class CreateService {

    @Test
    @DisplayName("Should create a new service from a service Dto and return it")
    void createNewServiceAndReturnServiceDtoWhenPassedAValidServiceDto() {
      UUID serviceDtoId = UUID.randomUUID();
      ServiceDto serviceDto = new ServiceDto(serviceDtoId, "netflix", "Netflix", new ArrayList<>());
      Service newService = new Service(serviceDtoId, "netflix", "Netflix", new ArrayList<>(), LocalDateTime.now(),
          LocalDateTime.now());

      // when
      when(serviceRepository.findByName("netflix")).thenReturn(Optional.empty());
      when(serviceMapper.fromDto(serviceDto)).thenReturn(newService);
      when(serviceRepository.save(newService)).thenReturn(newService);
      when(serviceMapper.toDto(newService)).thenReturn(serviceDto);

      // assert
      ServiceDto result = serviceService.createService(serviceDto);
      assertNotNull(result);
      assertEquals(serviceDtoId, result.id());
      assertEquals("netflix", result.name());
      assertEquals("Netflix", result.displayName());
    }

    @Test
    @DisplayName("Should throw ResourceAlreadyExistsException if service already exists")
    void throwResourceAlreadyExistsExceptionWhenPassedDuplicateService() {
      UUID serviceDtoId = UUID.randomUUID();
      ServiceDto serviceDto = new ServiceDto(serviceDtoId, "netflix", "Netflix", new ArrayList<>());
      Service existingService = new Service(UUID.randomUUID(), "netflix", "Netflix", new ArrayList<>(),
          LocalDateTime.now(), LocalDateTime.now());

      // when
      when(serviceRepository.findByName("netflix")).thenReturn(Optional.of(existingService));

      // assert
      assertThrows(ResourceAlreadyExistsException.class, () -> {
        serviceService.createService(serviceDto);
      });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException if passed invalid arguments")
    void throwIllegalArgumentExceptionWhenPassedInvalidArguments() {
      ServiceDto serviceDtoWithNullName = new ServiceDto(UUID.randomUUID(), null, "Display Name", new ArrayList<>());
      ServiceDto serviceDtoWithEmptyName = new ServiceDto(UUID.randomUUID(), "", "Display Name", new ArrayList<>());
      ServiceDto serviceDtoWithNullDisplayName = new ServiceDto(UUID.randomUUID(), "Netflix", null, new ArrayList<>());
      ServiceDto serviceDtoWithEmptyDisplayName = new ServiceDto(UUID.randomUUID(), "Netflix", "", new ArrayList<>());

      assertThrows(IllegalArgumentException.class, () -> {
        serviceService.createService(serviceDtoWithNullName);
      });
      assertThrows(IllegalArgumentException.class, () -> {
        serviceService.createService(serviceDtoWithEmptyName);
      });
      assertThrows(IllegalArgumentException.class, () -> {
        serviceService.createService(serviceDtoWithNullDisplayName);
      });
      assertThrows(IllegalArgumentException.class, () -> {
        serviceService.createService(serviceDtoWithEmptyDisplayName);
      });
    }
  }
  @Nested
  @DisplayName("Update service should update a service or throw an exception")
  class UpdateService {

    @Test
    @DisplayName("Should update an existing service and return the updated service DTO")
    void updateExistingServiceAndReturnUpdatedServiceDto() {
      UUID serviceId = UUID.randomUUID();

      ServiceDto serviceDto = new ServiceDto(serviceId, "new spotify name", "new Spotify display", new ArrayList<>());
      Service existingService = new Service(serviceId, "spotify", "Spotify", new ArrayList<>(), LocalDateTime.now(),
          LocalDateTime.now());
      Service expectedUpdatedService = new Service(serviceId, serviceDto.name(), serviceDto.displayName(),
          new ArrayList<>(),
          LocalDateTime.now(), LocalDateTime.now());
      ServiceDto expectedUpdatedServiceDto = new ServiceDto(serviceId, serviceDto.name(), serviceDto.displayName(),
          new ArrayList<>());

      when(serviceRepository.findById(serviceDto.id())).thenReturn(Optional.of(existingService));
      when(serviceRepository.save(expectedUpdatedService)).thenReturn(expectedUpdatedService);
      when(serviceMapper.toDto(expectedUpdatedService)).thenReturn(expectedUpdatedServiceDto);

      ServiceDto result = serviceService.updateService(serviceId, serviceDto);
      assertNotNull(result);
      assertEquals(expectedUpdatedServiceDto, result);
    }

    @Test
    @DisplayName("Throw ResourceNotFound exception when service doesn't exist")
    void throwResourceNotFoundExceptionWhenServiceDoesNotExist() {
      UUID serviceId = UUID.randomUUID();

      ServiceDto serviceDto = new ServiceDto(serviceId, "youtube", "Youtube", new ArrayList<>());

      when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());

      assertThrows(ResourceNotFoundException.class, () -> {
        serviceService.updateService(serviceId, serviceDto);
      });
    }

    @Test
    @DisplayName("Throw IllegalArgumentException when no new value is provided")
    void throwIllegalArgumentExceptionWhenPassedNoNewValue() {
      UUID serviceId = UUID.randomUUID();

      ServiceDto serviceDto = new ServiceDto(serviceId, "spotify", "Spotify", new ArrayList<>());
      Service service = new Service(serviceId, "spotify", "Spotify", new ArrayList<>(), LocalDateTime.now(),
          LocalDateTime.now());

      when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));

      assertThrows(IllegalArgumentException.class, () -> {
        serviceService.updateService(serviceId, serviceDto);
      });
    }
  }
  @Nested
}
