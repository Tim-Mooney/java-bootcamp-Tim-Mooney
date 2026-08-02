**Which design decision most affected correctness?**

Making the BusinessException class in lab 16 has made lab 17 more correct. Also, keeping the jacoco target reasonable, because 100% coverage is unnecessary and unreasonable.

**What evidence proves the implementation works?**

The test suite with the coverage and all the tests passing.

**Which failure was hardest to diagnose?**

Thread.sleep(). It didn't break anything here, but it could later.

failure 3: the tests run the same way, so the outcome is the same
failure 4: sleeps make the tests take longer and could break if anything timing related is happening in the test
