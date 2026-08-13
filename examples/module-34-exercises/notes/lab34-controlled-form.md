# Lab 34 — Controlled Form Sketch

## Reference

| UI piece | State field |
| --- | --- |
| Name input | name |
| Status select | status |
| Error text | error |
| Submit disabled | isValid derived |

## Step 2 — Flow

render → onChange updates state → validate → onSubmit.

## Step 3 — Fixture

name `Ravi Singh`, status `ACTIVE` before submit assigns `CUS-1002` (server later).

## Step 4 — Uncontrolled note

uncontrolled refs are out of scope for this lab path.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.