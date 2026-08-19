package com.northstar.crm.account;

import org.junit.jupiter.api.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.serverError;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;


@SpringBootTest
class AccountProfileResilienceTest {

  @RegisterExtension
  static WireMockExtension wm = WireMockExtension.newInstance()
          .options(wireMockConfig().dynamicPort()).build();

  @DynamicPropertySource
  static void props(DynamicPropertyRegistry r) { r.add("account.api.base-url", wm::baseUrl); }

  @Autowired private AccountProfileService service;

  @Autowired
  private CircuitBreakerRegistry circuitBreakerRegistry;

  @BeforeEach
  void reset() {
    wm.resetAll();
    circuitBreakerRegistry.circuitBreaker("accountProfile").reset();
  }

  @Test
  void returnsSummaryOnSuccess() throws Exception {
    wm.stubFor(get(urlPathMatching("/accounts/CUS-1/summary"))
            .willReturn(okJson("{\"customerId\":\"CUS-1\",\"available\":true,\"note\":\"ok\"}")));
    assertThat(service.find("CUS-1").get().available()).isTrue();
  }

  @Test
  void fallsBackOn5xxAfterRetries() throws Exception {
    wm.stubFor(get(urlPathMatching("/accounts/CUS-2/summary")).willReturn(serverError()));
    assertThat(service.find("CUS-2").get()).isEqualTo(AccountSummary.unavailable("CUS-2"));
    wm.verify(3, getRequestedFor(urlPathMatching("/accounts/CUS-2/summary"))); // maxAttempts
  }

  @Test
  void fallsBackOnTimeout() throws Exception {
    wm.stubFor(get(urlPathMatching("/accounts/CUS-3/summary"))
            .willReturn(okJson("{}").withFixedDelay(2000))); // > 1.5s timeoutDuration
    assertThat(service.find("CUS-3").get()).isEqualTo(AccountSummary.unavailable("CUS-3"));
  }

  @Test
  void circuitOpensAndFailsFast() throws Exception {
    wm.stubFor(get(urlPathMatching("/accounts/CUS-4/summary")).willReturn(serverError()));
    for (int i = 0; i < 4; i++) service.find("CUS-4").get(); // trip breaker
    wm.resetRequests();

    assertThat(service.find("CUS-4").get()).isEqualTo(AccountSummary.unavailable("CUS-4"));
    wm.verify(0, getRequestedFor(urlPathMatching("/accounts/CUS-4/summary"))); // OPEN = no call made
  }
}