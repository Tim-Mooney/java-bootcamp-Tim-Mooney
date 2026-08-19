# Lab 40 — Plan Dependency-Check Gate

## Step 1 — Profile sketch

 `-Psecurity-scan`: plugin goal, HTML+JSON reports, and a CVSS fail threshold placeholder.

## Step 2 — Check the reference

Confirm JDK 21 + Maven Wrapper habits: `./mvnw -B -Psecurity-scan dependency-check:check` from the CRM module root.

## Step 3 — Suppression policy draft

required fields for any suppression: CVE id, owner, expiry date. State that silent suppressions fail the gate.

## Step 4 — Folder prep

Create note paths for sanitized HTML/JSON under `notes/screenshots/lab-40/`

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.