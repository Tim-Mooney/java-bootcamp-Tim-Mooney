# Which design decision most affected correctness (transaction boundary size)?
- @Transactional wrapping the transfer method. This makes everything fail together if any part fails.
# What evidence proves rollback works?
- Test case 1
# Which failure was hardest (proxy / self-invocation / exception type)?
- Self-invoke this.transfer from non-TX method because the rollback didn't happen and I didn't notice until I checked further.