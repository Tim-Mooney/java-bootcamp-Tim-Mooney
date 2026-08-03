# Lab 18 — Stub vs Verify

## Stub (arrange)
when(repo.findById("CUS-1002")).thenReturn(raviProspect)

## Verify (assert collaboration)
verify(repo).save()

## One sentence — both roles
stubs feed inputs; verifies prove side-effect calls.

## Scope
Pre-lab only.