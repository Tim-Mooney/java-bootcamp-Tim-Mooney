# Lab 34 — State notes

## Lifted state

why create/edit mode lives in App, not in CustomerCard.
Because mode is in app. CustomerCard cannot change mode so each CustomerCard would need to know if it's being edited.
Also, only one card would be edited at a time, so they would need to know about each other.

## Validation

client validation is UX only; server re-validates in Lab 35.
