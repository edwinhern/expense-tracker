package com.expense_tracker.backend.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.plaid.client.ApiClient;
import com.plaid.client.request.PlaidApi;

@Configuration
public class PlaidConfig {
  @Value("${plaid.client-id}")
  private String clientId;

  @Value("${plaid.secret}")
  private String secret;

  @Bean
  public PlaidApi client() {
    final HashMap<String, String> apiKeys = new HashMap<>();
    apiKeys.put("clientId", clientId);
    apiKeys.put("secret", secret);
    apiKeys.put("plaidVersion", "2020-09-14");

    final ApiClient apiClient = new ApiClient(apiKeys);
    apiClient.setPlaidAdapter(ApiClient.Sandbox);

    return apiClient.createService(PlaidApi.class);
  }
}
