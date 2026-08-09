# Lab 20 — Rewrite Unsafe Logs

## Unsafe example
logging a full customer profile with SSN, email, any PII

## Safe rewrite (Amina/CUS-1001)
logging CUS-1001, Customer.status, Lab-request-001

## Safe Ravi activate start
log.INFO customerId=CUS-1002 status=PROSPECT correlation=lab-request-001

## Scope
Pre-lab only.