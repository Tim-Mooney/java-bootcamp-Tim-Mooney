# Lab 30 — Topic and Key Map

## Reference

| Concept | Northstar choice |
| --- | --- |
| Main topic | crm.customer-events.v1 |
| DLQ topic | crm.customer-events.v1.dlq |
| Partitions (lab) | 3 |
| Record key | customerId (e.g. CUS-1001) |

## Step 2 — Keying reason

Write why keying by `CUS-1001` / `CUS-1002` keeps a customer's events ordered within a partition.
Keying by customer id (CUS-1001, CUS-1002) forces the broker to hash every event for a given customer to the same partition every time, so all of CUS-1001's events queue up behind each other in the exact order they were published

## Step 3 — Versioning

Explain what the `.v1` suffix buys the team when the payload schema changes later.
.v1 buys the team the ability to change the payload schema later without breaking every existing consumer at once.

## Step 4 — DLQ trigger

List two failure cases that should land a record in the DLQ (conceptual only).
Malformed payload or retries exhausted

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.