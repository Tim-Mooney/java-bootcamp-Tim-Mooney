# Lab 24 — SOAP Fault Versus REST Error

| Case | SOAP | REST                     |
| --- | -- |--------------------------|
| Missing customer | SOAP Fault (Client/business) | 404 JSON problem details |
| Validation fail | SOAP Fault | 400 JSON                 |
| Missing UsernameToken | WS-Security fault |401/403 (later Lab 28)                  |

## One rule
Same CustomerService exception; different protocol adapters.


## Scope
Pre-lab only.