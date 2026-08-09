# Lab 27 — Transfer Pseudocode

## Annotation / method
@Transactional
transfer(from, to, amount, correlation):

## Force-fail check
if to == ACC-FORCE-FAIL: throw

## Money steps
load accounts
debit from; credit to

## Log step
write TransactionLog(correlation)

## Scope
Pre-lab only.