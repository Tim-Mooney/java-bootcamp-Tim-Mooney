# Lab 31 — Spring Kafka notes

## Publish path

When a customer is created or updated, the service builds a `CustomerEvent`
record and calls `CustomerEventPublisher.publish(event)`.

That method sends it via `KafkaTemplate` to the topic configured in
`crm.kafka.customer-events-topic` (`crm.customer-events.v1`), using
`event.customerId()` as the record key. The send is
async; `whenComplete` logs `customer_event_published` on success (with
partition/offset) or `customer_event_publish_failed` on error.


## Idempotency

`ProcessedEventStore` wraps a `ConcurrentHashMap`-backed `Set<String>` of
seen `eventId`s. `markIfNew(eventId)` is really just `Set.add(eventId)` —
it returns `true` only the first time a given eventId is seen, `false` on
every call after that.
