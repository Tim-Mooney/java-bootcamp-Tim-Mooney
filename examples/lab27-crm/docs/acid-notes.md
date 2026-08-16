# Lab 27 — ACID evidence

| Property     | Lab evidence                                                  |
|--------------|---------------------------------------------------------------|
| Atomicity    | Failed ACC-FORCE-FAIL leaves MAIN unchanged; no log           |
| Consistency  | Balances stay non-negative for the happy path you ran         |
| Isolation    | State expectation for concurrent transfers                    |
| Durability   | After success, restart caveats for H2 mem vs file/PostgreSQL  |
