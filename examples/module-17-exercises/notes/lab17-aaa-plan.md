# Lab 17 — AAA Service Tests Plan

## Step 1 — Happy path

AAA for activate Ravi PROSPECT → ACTIVE.
Arrange: Validator, Repository, Notifier, Service.
Act: service.activate(CUS-1002).
Assert: CUS-1002 is active.

## Step 2 — Not found

AAA for CUS-9999 throws not found.
Arrange: Validator, Repository, Notifier, Service.
Act: service.activate(CUS-9999).
Assert: throws NotFound.

## Step 3 — Illegal

AAA for illegal transition on Amina ACTIVE.
Arrange: Validator, Repository, Notifier, Service.
Act: service.activate(CUS-1001).
Assert: throws domain exception.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.