# Lab 30 — Kafka notes (timed path)

## Produce → consume

TODO: 2–3 sentences on produce → broker → consume for a customer event.

## Keying

TODO: why key = `customerId` (ordering per customer).

## DLQ

TODO: purpose of `crm.customer-events.v1.dlq` for Lab 31.

replay/--from-beginning is a learning tool. Production replay needs a policy and idempotent consumers.


| Item | Lab value                                                  |
| ---- |------------------------------------------------------------|
| Bootstrap (host) | `localhost:9092`                                           |
| Primary topic | `crm.customer-events.v1` (3 partitions)                    |
| DLQ topic | `crm.customer-events.v1.dlq` (1 partition)                 |
| Record key | `customerId` (`CUS-1001`, `CUS-1002`, `CUS-1003`)           |
| Sample correlation | `lab-request-001`                                          |
| Demo groups | `crm-notifications` (competing), `crm-audit` (independent) |
