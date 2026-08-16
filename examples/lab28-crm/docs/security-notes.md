# Lab 28 — Security notes

# 401 vs 403 in one sentence each
- 401 - Unauthorized. The token is missing or wrong, so you can't be authorized.
- 403 - Forbidden. The token is present, but you can't do whatever you tried to do.
# local HS256 secret vs production IdP / JWKS / rotation
- Local secret is just a static value for all tokens that works for testing in this small scale.
- Production would be more secure, with the program not having a single secret for every token, and implement lifetime.
- Fixtures: CUS-1001 / CUS-1002; correlation lab-request-001
