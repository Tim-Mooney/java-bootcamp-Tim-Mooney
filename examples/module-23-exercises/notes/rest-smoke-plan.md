# Lab 23 — REST Smoke Plan

## Start command
mvn spring-boot:run

## Health check
GET /actuator/health → UP

## CUS-1001 steps
POST /api/customers for CUS-1001 (Amina, ACTIVE) with correlation lab-request-001
GET /api/customers/CUS-1001

## CUS-1002 steps
POST /api/customers for CUS-1002 (Ravi, PROSPECT) with correlation lab-request-001
GET /api/customers/CUS-1002

## Correlation header/id
lab-request-001

## Scope
Pre-lab only.