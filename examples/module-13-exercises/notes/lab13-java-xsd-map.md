# Lab 13 — Java to XSD Map

## Reference

| Java idea | XSD idea | Example           |
| --- | --- |-------------------|
| String customerId | xsd:string | CUS-1001          |
| String fullName | xsd:string | Amina Khan        |
| enum/status | xsd:string or enum | ACTIVE / PROSPECT |
| String customerId | xsd:string | CUS-1002          |
| String fullName | xsd:string | Ravi Sigh         |
| enum/status | xsd:string | PROSPECT |

## Step 2 — Id pattern

Propose a documentation pattern `CUS-####` (not enforced in code yet).
String customerId | xsd:string | CUS-#### //whatever number for ID

## Step 3 — Honesty

Note: mapping in this notes file. ≠ generated JAXB yet.

## Step 4 — Boundary

Mark: hosting/codegen with Spring-WS is Lab 24, not this prep.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.