# Lab 16 — error model notes

| Status | Code | When                                  |
| ------ | ---- |---------------------------------------|
| 400 | VALIDATION_FAILED | Bean Validation on request DTO        |
| 404 | CUSTOMER_NOT_FOUND | Unknown customer id                   |
| 409 | BUSINESS_CONFLICT | Illegal status transition / duplicate |
|500|INTERNAL_ERROR | Unexpected internal error             |

Correlation id: `lab-request-001`

I chose 409 for illegal transition because it is a Business confilict, there is nothing wrong with transitioning to ACTIVE.
422 would be like ACTIVE -> SLEEPY, SLEEPY is never a state.

