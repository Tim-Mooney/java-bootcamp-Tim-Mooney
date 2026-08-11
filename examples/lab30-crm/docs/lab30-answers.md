Failure 2: The CUS-9999 is out of order because the key is wrong.

# Which design decision most affected correctness (keying by customerId)?
- Keying every record by customerId rather than sending null keys or some other field. This single choice is what guarantees per-customer ordering: since Kafka only guarantees order within a partition, and the default partitioner routes all records with the same key to the same partition deterministically, every CUS-1001 event lands on the same partition in send order.
# What evidence proves produce/consume works end-to-end?
- The offset progression
# Which failure was hardest to diagnose (lag, rebalance, advertised listeners)?
- The wrong key, they were out of order and hard to find because there was a ton of correct entries.