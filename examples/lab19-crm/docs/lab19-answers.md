## Which design decision most affected correctness (Page Object vs inline locators)?
- The inline indicators.
## What evidence proves the implementation works?
- The tests work together and provide a comprehensive test of all the code. The tests all pass.
## Which failure was hardest to diagnose (driver mismatch, wait timeout, API JSON)?
- Throttle/delay api. There were no error messages, so it was hard to identify that something was wrong.

Failure 2 is in CustomerApiIT.java
Failure 3 is in CustomerUiIT.java
