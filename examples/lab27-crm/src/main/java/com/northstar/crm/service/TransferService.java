package com.northstar.crm.service;

import com.northstar.crm.account.Account;
import com.northstar.crm.account.AccountRepository;
import com.northstar.crm.account.TransactionLogRepository;
import com.northstar.crm.account.TransactionLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class TransferService {
  private final AccountRepository accountRepository;
  private final TransactionLogRepository transactionLogRepository;

  public TransferService(AccountRepository accountRepository,
                         TransactionLogRepository transactionLogRepository) {
    this.accountRepository = accountRepository;
    this.transactionLogRepository = transactionLogRepository;
  }

  @Transactional
  public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Amount must be positive");
    }

    Account from = accountRepository.findById(fromAccountId)
        .orElseThrow(() -> new IllegalArgumentException("Unknown from account"));
    if(toAccountId.equals("ACC-FORCE-FAIL")){
      throw new IllegalStateException("Forced transfer failure for rollback demo");
    }
    Account to = accountRepository.findById(toAccountId)
        .orElseThrow(() -> new IllegalArgumentException("Unknown to account"));

    if (from.getBalance().compareTo(amount) < 0) {
      throw new IllegalStateException("Insufficient funds in " + fromAccountId);
    }

    from.setBalance(from.getBalance().subtract(amount));
    to.setBalance(to.getBalance().add(amount));
    accountRepository.save(from);
    accountRepository.save(to);

    TransactionLog log = new TransactionLog();
    log.setFromAccountId(fromAccountId);
    log.setToAccountId(toAccountId);
    log.setAmount(amount);
    transactionLogRepository.save(log);
  }
}
