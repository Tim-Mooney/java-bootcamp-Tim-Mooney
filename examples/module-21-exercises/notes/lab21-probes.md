# Lab 21 — Liveness vs Readiness

## Liveness
process stuck → restart (e.g., deadlocked threads).

## Readiness
dependency down → not ready, keep process.

## Wrong mix
restarting on transient DB outage.

## Lab expectation
toggle CrmReadinessIndicator OUT_OF_SERVICE; liveness stays UP.

## Scope
Pre-lab only.