**Which design decision most affected correctness?**

Making a HashMap instead of a List, because it allows for unique IDs and faster lookups. 
Eliminated the == bugs.

**What evidence proves the refactor preserves intended behavior?**

The tests pass, and the behavior of the methods is consistent with the intended functionality. The refactored code maintains the same input-output relationships as the original code, ensuring that existing functionality is preserved.

**Which smell was hardest to justify removing?**

Maybe the logging? It doesn't affect correctness.
