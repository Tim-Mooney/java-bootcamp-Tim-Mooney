# Lab 13 — Contract-First Mindset

## Step 1 — Definition

One sentence: define types and operations in XSD/WSDL before generating Java.
types - values and objects; operations - service methods.


## Step 2 — Risk of code-first

Name two risks: accidental breaking changes and framework leakage into the contract.
Leaking implementation details into the contract can cause coupling and make it harder to change the service without affecting clients.
accidental breaking changes can occur when code-first development leads to changes in the generated contract that are not backward compatible, potentially breaking existing clients.

## Step 3 — CRM ops

List candidate ops: GetCustomer, ActivateCustomer (paper names only).

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.