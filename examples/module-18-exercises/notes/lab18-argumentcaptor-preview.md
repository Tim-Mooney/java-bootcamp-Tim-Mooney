# Lab 18 — ArgumentCaptor Preview

## Declare
Declare ArgumentCaptor<Customer> captor.

## Verify + capture
verify(repo).save(captor.capture()).

## Assert
captor.getValue().getStatus() == ACTIVE for Ravi.

## Scope
Pre-lab only.