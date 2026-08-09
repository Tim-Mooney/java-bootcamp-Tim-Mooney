# Lab 20 — logging contract

## MDC keys

| Key | Meaning |
| --- | ------- |
| corr | X-Correlation-Id |
| cust | customerId |
| op | create / get |

## Rules

- Never log fullName or email
- Always `MDC.clear()` in filter `finally`

## TODO

corr=lab-request-001 cust=CUS-1001 op=customer.create
corr=lab-request-001 cust=CUS-1002 op=customer.get


## Logging contract

- Required MDC: correlationId, customerId (when known), op (`create` / `get`)
- Pattern: corr=%X{correlationId} cust=%X{customerId} op=%X{op}
- Allowed: customerId, status, reason codes, durations, HTTP status
- Forbidden: fullName, email, phone, address, passwords, tokens, PAN
- Correlation header: X-Correlation-Id (example lab-request-001)
- Levels: INFO success path; WARN business reject; ERROR unexpected
- Filter owns MDC.clear() in finally
- Production: ship to central store; never embed secrets in patterns

Even with -q the output is too long to screenshot, but I ran it twice and it passed both times.