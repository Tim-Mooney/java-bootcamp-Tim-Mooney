# Lab 40 — Draft AppSec Go/No-Go Questions

## Step 1 — Questions

Draft five go/no-go questions (High CVE owned?, secrets in Git?, authz negative test?, suppression policy?, verify still green?).
- Are all High CVEs in this change either remediated or explicitly assigned an owner? If so, go. Unowned high CVE could impact customers by them getting their PII leaked
- Are there secrets in git status? If so, no go. Secrets in git might customer PII or keys to access it.
- Does the test suite include at least one negative-path test? If so, go. If there are no negative path tests, the application might crash badly if customers try to use it.
- If any finding is suppressed, does it have a documented justification? If so, go. If the suppression should not have been ignored, unexpected behavior could impact customer experience.
- After any changes, did the tests still pass? If so, go. If the tests don't pass after a change then the application doesn't work as intended.

## Step 2 — Check the reference

no ship on raw scanner volume; no silent suppressions; no secrets.

## Step 3 — Tie to CRM

For each question, one line on impact to agents serving Amina/Ravi.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.