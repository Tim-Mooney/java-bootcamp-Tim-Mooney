# Lab 40 — Map CRM Attack Surfaces

## Reference

| Surface | OWASP theme | Example |
| --- | --- | --- |
| Customer GET/PUT API | Broken access control | Agent reads CUS-1001 |
| Search query params | Injection | Name/email filters |
| pom.xml deps | Vulnerable components | Transitive CVE |
| application.yml secrets | Security misconfiguration | DB password in Git |
| Actuator endpoints | Security misconfiguration | Unprotected /env |

## Step 1 — Inventory touchpoints

In notes, list at least five surfaces for the Spring CRM that serves agents looking up `CUS-1001` (Amina Khan) and `CUS-1002` (Ravi Singh): 
HTTP APIs (PII), JWT/RBAC (IDs), SQL/JPA (PII), file/log sinks (IDs), and (later) Kafka (PII). Mark which hold PII vs IDs.

## Step 2 — Check the reference

Compare your list to OWASP themes: injection, broken access control, security misconfiguration, vulnerable components, logging/monitoring failures.

## Step 3 — Rank top three

Pick the three highest-risk surfaces for a release gate before containers. Write one sentence of business impact per item.
HTTP APIs - If there is an unmasked field or some way to get PII from the API it could cause a leak.
SQL/JPA - If some user has too much permission, they can expose PII.
Kafka - Events are durable and fan out to every downstream consumer, a PII leak here multiplies instantly across many systems

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.