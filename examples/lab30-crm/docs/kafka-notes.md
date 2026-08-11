# Lab 30 — Kafka notes (timed path)

## Produce → consume

A producer serializes a customer event and sends it to a broker keyed by customerId.
The broker appends it to the log on whichever partition that key hashes to.
Then it sits there and waits to be consumed by a consumer

## Keying

Key = customerId because Kafka only guarantees ordering within a partition, not across the whole topic, and the default partitioner routes every record with the same key to the same partition

## DLQ

crm.customer-events.v1.dlq exists so Lab 31's consumer(s) have somewhere to route records they can't process instead of blocking the partition or losing data

replay/--from-beginning is a learning tool. Production replay needs a policy and idempotent consumers.


| Item | Lab value                                                  |
| ---- |------------------------------------------------------------|
| Bootstrap (host) | `localhost:9092`                                           |
| Primary topic | `crm.customer-events.v1` (3 partitions)                    |
| DLQ topic | `crm.customer-events.v1.dlq` (1 partition)                 |
| Record key | `customerId` (`CUS-1001`, `CUS-1002`, `CUS-1003`)           |
| Sample correlation | `lab-request-001`                                          |
| Demo groups | `crm-notifications` (competing), `crm-audit` (independent) |
