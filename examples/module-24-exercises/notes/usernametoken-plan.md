# Lab 24 — UsernameToken Plan

## Where credentials live
Header: wsse UsernameToken (lab user + lab password)

## Success case
secured GetCustomer for CUS-1001

## Failure case
missing/wrong token → security fault before service call

## Out of scope
full signatures, SAML, OAuth IdP

## Scope
Pre-lab only.