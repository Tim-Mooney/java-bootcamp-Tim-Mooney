# Lab 18 — Mockito Anti-Patterns

| Anti-pattern | Better                                     |
| --- |--------------------------------------------|
| Mock the SUT | Mock collaborators only                    |
| Unnecessary stubbing | stub what is actually used, not everything |
| verifyNoMoreInteractions always |Use when interaction surface is critical       |

## AI reject rule
Reject suggestions that mock CustomerService while testing CustomerService.
Fixtures: Ravi/Amina/CUS-9999 as appropriate.

## Scope
Pre-lab only.