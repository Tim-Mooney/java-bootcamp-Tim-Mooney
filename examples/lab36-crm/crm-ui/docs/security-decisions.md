# Lab 36 — Security decisions

## Token storage

The barer token lives in tokenStore.ts and is never written to local or session storage, so XSS can't work because the token isn't there.

## 401 vs 403

TODO: expire/re-auth vs forbidden.
401 - unauthorized. This is when there is no token, a malformed token, or the token is expired.
403 - forbidden. The token is there and it is right, but it doesn't grant permission for whatever the user is trying to access.

## CSRF

A malicious third-party site cannot make the victim's browser attach that header on its behalf; it has no ambient access to the in-memory tokenStore value at all
