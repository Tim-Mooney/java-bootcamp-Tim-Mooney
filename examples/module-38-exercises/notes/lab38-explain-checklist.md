# Lab 38 — EXPLAIN Checklist

## Step 1 — Command

Write the command you will use: `EXPLAIN (ANALYZE, BUFFERS) <sql>;`.
EXPLAIN (ANALYZE, BUFFERS) SELECT customer_id, full_name, status FROM customer WHERE customer_id = 'CUS-1001';

## Step 2 — Look for

Seq Scan vs Index Scan, rows estimates, buffers.
seq scan - scan each row
index scan - walks a B-tree, gets matching TIDs, then fetches each row from the heap
FILTER EARLY TO KEEP COST LOW

## Step 3 — Success signal

Index Scan on customer_id for Amina lookup is a good sign.

## Step 4 — Analyze

Note `ANALYZE customer;` updates stats (PostgreSQL), not DBMS_STATS.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.