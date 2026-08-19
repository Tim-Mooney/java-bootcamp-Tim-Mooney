# Lab 32 — Resilience notes

## Instance name

why all annotations share `accountProfile`?
It has to be the same string on all three because there's exactly one call site they're
all protecting


## Truthful fallback

why `available=false` must never look like a successful funded account?
there would be no way to tell if an account is representing unknown data and fallback would break because nobody knows if the account is correct.

