# Lab 30 — Event Envelope Sketch

## Step 1 — Headers

List envelope fields you will use: `eventType`, `eventVersion`, `occurredAt`, `correlationId`, `customerId`, `payload`.

## Step 2 — Amina sample

in this notes file., sketch `CustomerCreated` for `CUS-1001` Amina Khan with `correlationId=lab-request-001`.
POST -> API -> Controller -> Service -> Response -> everything picks up .v1 off the broker
## Step 3 — Ravi sample

Sketch `CustomerStatusChanged` for `CUS-1002` Ravi Singh (`ACTIVE` → `SUSPENDED` or similar).
SOAP updateStatus -> commits .v1

## Step 4 — Compatibility note

consumers must ignore unknown payload fields (forward compatible).

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.