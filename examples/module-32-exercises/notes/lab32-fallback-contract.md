# Lab 32 — Fallback Contract

## Step 1 — Fields kept

customerId, displayName maybe, status UNKNOWN.

## Step 2 — Fields dropped

balance, tier, lastLogin.

## Step 3 — API signal

Decide: HTTP 200 with `degraded=true` vs 503 — pick one and justify.
HTTP 200 because some parts are still working, its just degraded. 503 is if the entire service is unavailable.

## Step 4 — User message

UI string: *Account details temporarily limited.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.