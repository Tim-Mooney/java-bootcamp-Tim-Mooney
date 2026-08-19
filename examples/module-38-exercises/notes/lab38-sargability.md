# Lab 38 — Sargability

## Reference

| Predicate | Sargable? |
| --- | --- |
| customer_id = 'CUS-1001' | Yes |
| status = 'ACTIVE' | Yes (with index) |
| LOWER(full_name) = 'amina khan' | Usually no on plain index |
| created_at >= TIMESTAMP '2026-01-01' | Yes (range) |
| date_trunc('day', created_at) = ... | Often weaker than range |

## Step 2 — Rewrite

Rewrite a non-sargable name search idea into something index-friendlier (e.g. store lowercased column or use `ILIKE` carefully).
LOWER(full_name) just store names lowercased or create another column for lowercased names then just full_name = 'amina khan'

## Step 3 — Half-open range

Prefer `created_at >= d AND created_at < d+1` over wrapping columns in functions.

## Step 4 — Oracle note

If old materials say `TRUNC(created_at)`, map to PostgreSQL range/`date_trunc` contrast.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.