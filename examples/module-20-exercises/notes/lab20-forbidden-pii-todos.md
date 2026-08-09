# Lab 20 — Fill Forbidden PII Checklist TODOs

Forbidden: email
Forbidden: phone
Forbidden: ssn / ID
Allowed customerId: customerId (CUS-1001, etc.)
Allowed correlation: lab-request-001
Clear MDC in finally? always

## Finally snippet
try { … } finally { MDC.clear(); }

## Scope
Pre-lab only.