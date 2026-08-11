# Lab 28 — SecurityFilterChain Sketch

## Session policy
STATELESS

## Login matcher
/api/auth/login → permitAll

## Customers matcher + roles
/api/customers/** → hasAnyRole(AGENT, ADMIN)

## Admin matcher + roles
/api/admin/** → hasRole(ADMIN)

Other APIs → authenticated (default deny extras)

JWT filter before UsernamePasswordAuthenticationFilter.

## Scope
Pre-lab only.




Other APIs → authenticated (default deny extras)

JWT filter before UsernamePasswordAuthenticationFilter.
