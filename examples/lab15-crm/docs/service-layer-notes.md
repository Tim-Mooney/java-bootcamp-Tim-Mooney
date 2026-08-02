# Lab 15 — service layer notes

## Status transition table

| From | Allowed to |
| ---- | ---------- |
| PROSPECT | ACTIVE, CLOSED |
| ACTIVE | SUSPENDED, CLOSED |
| SUSPENDED | ACTIVE, CLOSED |
| CLOSED | (none) |

## Wiring

- Shared `InMemoryCustomerRepository` instance for `CustomerValidator` + `DefaultCustomerService`
- No `HashMap` / JDBC / `EntityManager` in the `service` package

## Bean validation vs Customer Validation
Bean validation used @NotBlank, @Size, etc. before it even became a DTO, and just checked the size of things and if they were blank
CustomerValidator validates using ifs for business rules, not just if the data exists and is shaped right. 

## Same status decision

- Transitioning from ACTIVE -> ACTIVE results in an IllegalStateException.
