Failure 1: it propgates all the way up. Amina before the flip is fine, but Ravi never exists.
Failure 4: If i put a different repo in validator and service, validator can't actually validate anything because it is saved in the repo it cant see.
Failure 5: The validator checks the old status against the new status, but if old status is the new status before the check, its comparing new status and new status.
This is an exception for me.

**Which design decision most affected correctness?**

The layer flow. The same repo being in service and validator made everything work and until the failure experiment, I 
hadn't considered that everything would break if the repos were different. That could be really annoying to pinpoint.

**What evidence proves the implementation works?**

The failure experiments proved the status transitions, and the broken change status proved that the transitions are being checked properly.

**Which failure was hardest to diagnose?**

If I didn't know the mixed repo failure, I think that would have stumped me for a long time. 