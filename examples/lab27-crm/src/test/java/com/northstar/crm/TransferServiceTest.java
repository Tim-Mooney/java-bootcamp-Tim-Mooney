package com.northstar.crm;

import com.northstar.crm.account.AccountRepository;
import com.northstar.crm.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferServiceTest {
  @Autowired TransferService transferService;
  @Autowired AccountRepository accounts;

  @Test
  void forceFailRollsBack() {
    BigDecimal before = accounts.findById("ACC-MAIN-1001").orElseThrow().getBalance();
    assertThrows(Exception.class, () ->
        transferService.transfer("ACC-MAIN-1001", "ACC-FORCE-FAIL", new BigDecimal("10.00")));
    assertEquals(before, accounts.findById("ACC-MAIN-1001").orElseThrow().getBalance());
  }

  @Test
  void happyPathMovesFunds(){
    // transfer 5.00 MAIN→LOYALTY
    BigDecimal beforeMain = accounts.findById("ACC-MAIN-1001").orElseThrow().getBalance();
    BigDecimal beforeLoyalty = accounts.findById("ACC-LOYALTY-1001").orElseThrow().getBalance();

    transferService.transfer("ACC-MAIN-1001", "ACC-LOYALTY-1001", new BigDecimal("500"));

    assertEquals(beforeMain.subtract(new BigDecimal("500")), accounts.findById("ACC-MAIN-1001").orElseThrow().getBalance());
    assertEquals(beforeLoyalty.add(new BigDecimal("500")), accounts.findById("ACC-LOYALTY-1001").orElseThrow().getBalance());


  }
}
