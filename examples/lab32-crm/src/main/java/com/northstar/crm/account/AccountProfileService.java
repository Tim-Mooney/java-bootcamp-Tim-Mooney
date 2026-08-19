package com.northstar.crm.account;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
public class AccountProfileService {

  private final AccountClient client;

  public AccountProfileService(AccountClient client) {
    this.client = client;
  }

  @CircuitBreaker(name = "accountProfile")
  @Retry(name = "accountProfile", fallbackMethod = "fallback")
  @TimeLimiter(name = "accountProfile")
  public CompletableFuture<AccountSummary> find(String customerId) {
    return CompletableFuture.supplyAsync(() -> client.fetch(customerId));
  }

  @SuppressWarnings("unused")
  private CompletableFuture<AccountSummary> fallback(String customerId, Throwable ex) {
    return CompletableFuture.completedFuture(AccountSummary.unavailable(customerId));
  }
}
