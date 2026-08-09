# Lab 27 — Transaction Boundary Placement

## Place annotation on
TransferService.transfer(...) with @Transactional

## Avoid
@Transactional on controller

## Why (one sentence)
proxy on Spring service bean; HTTP stays thin

## Self-invocation risk
this.transfer() inside same class skips proxy

## Scope
Pre-lab only.