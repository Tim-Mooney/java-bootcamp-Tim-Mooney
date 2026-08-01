**Which design decision most affected build correctness?**

Setting up the POM correctly was the most important design decision for build correctness. 
The POM defines the project structure, dependencies, and build process, so any misconfiguration can lead to build failures or incorrect behavior.

**What evidence proves the lifecycle walk was real (not only package once)?**

The ~/.m2 evidence from install and the tests run proves that the lifecycle walk was real. 
The evidence shows that the build process went through all the phases, including validate, compile, test, package, verify, and install.

**Which failure was hardest to diagnose?**

Removing scope from junit dependency was the hardest to diagnose.
It was easy to overlook on the tree.


FAILURE 3:

Running mvn install twice succeeded both times with no error; the second install overwrote the same SNAPSHOT artifact in ~/.m2/repository/com/northstar/customer-service/0.1.0-SNAPSHOT/ (confirmed via updated file timestamp). 
This is expected — SNAPSHOT installs are idempotent by design, unlike release versions.

FAILURE 4:

Run 1: Total time:  7.870 s

Run 2: Total time:  0.600 s

The first run needs to download dependencies, so it takes longer. 
The second run is faster because the dependencies are cached in the local Maven repository (~/.m2).
