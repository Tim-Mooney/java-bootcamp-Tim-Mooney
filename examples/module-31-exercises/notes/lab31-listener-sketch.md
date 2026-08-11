# Lab 31 — Listener Sketch

## Step 1 — Method outline

i`@KafkaListener(topics="crm.customer-events.v1", groupId="crm-notifications")` void onCustomerEvent(...).

## Step 2 — Second group

Sketch the audit listener with groupId `crm-audit` on the same topic.
@KafkaListener(
topics = "crm.customer-events.v1",
groupId = "crm-audit")
void onCustomerEventForAudit(CustomerEvent e) { ... }

## Step 3 — Payload type

typed CustomerEvent DTO - catch schema mistakes

## Step 4 — Correlation

Note where you will log `correlationId` / `lab-request-001` for support.
right after producer sends.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.