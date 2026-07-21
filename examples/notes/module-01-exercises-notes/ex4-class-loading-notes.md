
| # | Confirm | Your notes |
| - | ------- | ---------- |
| 1 | Code compiles and runs (or notes complete if analysis-only) | Pass  |
| 2 | You can explain the result in one sentence | Pass  |

java -verbose:class Hello | grep -E "Hello|String"
This runs Hello and prints every time it loads a class with "String" or "Hello" in it.
Without the grep, it shows a bunch of stuff security, etc.