package com.folkadev.folka_subs.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.folkadev.folka_subs.domain.dto.ServiceDto;
import com.folkadev.folka_subs.services.ServiceService;

@WebMvcTest(ServiceController.class)
public class ServiceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ServiceService serviceService;

  @Nested
  @DisplayName("GET /services")
  class GetServices {
    @Test
    void shouldReturn200AndFetchServices() throws Exception {
      List<ServiceDto> services = new ArrayList<>();
      UUID serviceAId = UUID.randomUUID();
      UUID serviceBId = UUID.randomUUID();
      services.add(new ServiceDto(serviceAId, "spotify", "Spotify", new ArrayList<>()));
      services.add(new ServiceDto(serviceBId, "amazon", "Amazon", new ArrayList<>()));

      when(serviceService.getServices()).thenReturn(services);

      mockMvc.perform(get("/services")).andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldReturn200AndAnEmptyListWhenThereAreNoServices() throws Exception {
      List<ServiceDto> services = new ArrayList<>();

      when(serviceService.getServices()).thenReturn(services);

      mockMvc.perform(get("/services")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }
  }

  @Nested
  @DisplayName("GET /services/{service_id}")
  class GetService {
    @Test
    void shouldReturnSpecificServiceWhenItExists() throws Exception {
      UUID serviceId = UUID.randomUUID();
      ServiceDto serviceDto = new ServiceDto(serviceId, "spotify", "Spotify", new ArrayList<>());

      when(serviceService.getService(serviceId)).thenReturn(Optional.of(serviceDto));

      mockMvc.perform(get("/services/{service_id}", serviceId)).andExpect(status().isOk())
          .andExpect(jsonPath("$.id").value(serviceDto.id().toString()))
          .andExpect(jsonPath("$.name").value(serviceDto.name().toString()))
          .andExpect(jsonPath("$.displayName").value(serviceDto.displayName().toString()));
    }

    @Test
    void shouldReturnNoFoundFoundWhenServicesDoesNotExist() throws Exception {
      UUID serviceId = UUID.randomUUID();

      when(serviceService.getService(serviceId)).thenReturn(Optional.empty());

      mockMvc.perform(get("/services/{service_id}", serviceId)).andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnErrorWhenInvalidIdIsPassed() throws Exception {
      mockMvc.perform(get("/services/{service_id}", "not-a-uuid"))
          .andExpect(status().isBadRequest());
    }
  }

  // TODO: Implement tests
  @Nested
  @DisplayName("POST /services")
  class CreateService {
    @Test
    void shouldReturn201WhenServiceIsCreated() throws Exception {

    }

    @Test
    void shouldReturn400WhenInvalidFieldsArePassed() throws Exception {

    }

    @Test
    void shouldReturn400WhenRequestBodyIsMissing() throws Exception {

    }

    @Test
    void shouldReturn409WhenServiceAlreadyExists() throws Exception {

    }
  }

  // TODO: Implement tests
  @Nested
  @DisplayName("PUT /services/{service_id}")
  class UpdateService {

    @Test
    void shouldReturn204WhenPassedValidFields() throws Exception {

    }

    @Test
    void shouldReturn404WhenServiceIsNotFound() throws Exception {

    }

    @Test
    void shouldReturn400WhenInvalidFieldsArePassed() throws Exception {

    }

    @Test
    void shouldReturn400WhenRequestBodyIsMissing() throws Exception {

    }
  }

  // TODO: Implement tests
  @Nested
  @DisplayName("DELETE /services/{service_id}")
  class DeleteService {

    @Test
    void shouldreturn204WhenServiceIsDeleted() throws Exception {

    }

    @Test
    void shouldReturn400WhenInvalidFieldsArePassed() throws Exception {

    }
  }
}
