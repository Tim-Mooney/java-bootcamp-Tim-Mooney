# Lab 27 — Propagation Warnings

| Pattern | Risk                       |
| --- |----------------------------|
| REQUIRES_NEW on log | Log commits; money rolls back |
| Self-invocation | @Transactional ignored     |
| Swallow exception | No rollback                |
| TX on controller | Wrong boundary             |

## Lab default
REQUIRED on TransferService.transfer

## Scope
Pre-lab only.