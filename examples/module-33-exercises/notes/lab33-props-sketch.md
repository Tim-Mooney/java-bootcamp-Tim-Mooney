# Lab 33 — Props Sketch

## Reference

| Prop | Example |
| --- | --- |
| customerId | CUS-1001 |
| name | Amina Khan |
| status | ACTIVE |
| onSelect | () => void |

## Step 2 — Types

Write TypeScript-ish types: `status: 'ACTIVE' | 'SUSPENDED' | ...`.
customerId: "CUS-XXXX"
name: "Name Name"
status: "ACTIVE" | "SUSPENDED" | "PROSPECT" | "CLOSED"
onSelect: () => void

## Step 3 — Children?

Decide whether `CustomerCard` takes `children` or only props — one sentence.
CustomerCard takes only props because I don't think there is any need for CustomerCard to take children.

## Step 4 — Anti-pattern

Note: do not pass the entire global store as one mega-prop.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.