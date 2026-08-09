# Lab 21 — Cardinality Anti-Patterns

| Label                         | OK?          |
|-------------------------------|--------------|
| outcome=success / failure     | yes          |
| customerId=CUS-1001           | no           |
| correlationId=lab-request-001 | no, use logs |

## Where ids go
Ids go in logs/traces

## Good metric sketch
crm.customer.create with outcome tag

## Scope
Pre-lab only.