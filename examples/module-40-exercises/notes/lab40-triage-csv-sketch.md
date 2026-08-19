# Lab 40 — Sketch Findings Triage CSV

## Reference

| Classification | Meaning |
| --- | --- |
| true_positive | Confirm and fix or accept with owner |
| false_positive | Document CPE/path mismatch |
| accepted_risk | Time-bounded, owned |
| fixed | Re-scan evidence required |

## Step 1 — Columns

Define CSV headers: finding_id, cve, cvss, dependency, path, classification, owner, due_date, notes.

## Step 2 — Check the reference

Classifications: `true_positive`, `false_positive`, `accepted_risk`, `fixed`. Accepted risk needs owner + expiry.

## Step 3 — Sample rows

Invent two synthetic rows (not real CVEs from production). One true_positive on a transitive JAR; one false_positive with rationale.
true_positive on JAR: probably remove the original dependency if you can
false_positive with rationale: documentation is wrong and so the code seems wrong but the behavior is correct and the doc is wrong

## Step 4 — CRM link

Note how a true_positive on the API layer could affect agents opening `CUS-1001` profiles—without claiming you are remediating today.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.