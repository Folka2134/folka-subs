package com.folkadev.folka_subs.controllers;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.folkadev.folka_subs.services.SubscriptionService;

@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private SubscriptionService subscriptionService;

  @Nested
  @DisplayName("GET /subscription/{subscription_id}")
  class GetSubscription {

    @Test
    void shouldReturn200AndSubscriptionDtoWhenValidIdIsPassed() throws Exception {

    }

    @Test
    void shouldReturn404WhenSubscriptionNotFound() throws Exception {

    }

    @Test
    void shouldReturn400WhenInvalidIdIsGiven() throws Exception {

    }
  }

  @Nested
  @DisplayName("POST /subscription/{subscription_id}")
  class CreateSubscription {

    @Test
    void shouldReturn201WhenSubscriptionIsCreated() throws Exception {

    }

    @Test
    void shouldReturn400WhenInvalidFieldsAreGiven() throws Exception {

    }

    @Test
    void shouldReturn400WhenRequestBodyIsMissing() throws Exception {

    }

    @Test
    void shouldReturn409WhenSubscriptionAlreadyExists() throws Exception {

    }
  }

  @Nested
  @DisplayName("PUT /subscription/{subscription_id}")
  class UpdateSubscription {

    @Test
    void shouldReturn200WhenSubscriptionIsUpdated() throws Exception {

    }

    @Test
    void shouldReturn404WhenSubscriptionNotFound() throws Exception {

    }

    @Test
    void shouldReturn400WhenInvalidFieldsAreGiven() throws Exception {

    }

    @Test
    void shouldReturn400WhenRequestBodyIsMissing() throws Exception {

    }
  }
}
