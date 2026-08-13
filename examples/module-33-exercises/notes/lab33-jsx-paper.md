# Lab 33 — JSX on Paper

## Step 1 — Tree

Sketch `<CustomerList>` containing two `<CustomerCard>` nodes.
<CustomerList>
<CustomerCard key="CUS-1001">
<h3>Amina Khan</h3>
<StatusBadge status="ACTIVE" />
</CustomerCard>
<CustomerCard key="CUS-1002">
<h3>Ravi Singh</h3>
<StatusBadge status="PROSPECT" />
</CustomerCard>
</CustomerList>

## Step 2 — Keys

Write why `key={customerId}` should be `CUS-1001`, not array index.
The key being the ID makes sure it goes in the right partition 

## Step 3 — Badge

Nest `<StatusBadge status="ACTIVE" />` inside Amina's card.

## Step 4 — No runtime

Do not create a Vite app in this exercise.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.