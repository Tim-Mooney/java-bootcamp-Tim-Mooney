| Artifact | Direct or transitive? | Scope shown |
| -------- |-----------------------|-------------|
| `junit-jupiter` | direct                | test        |
| `junit-jupiter-params` | transitive            | test        |

**What does -B mean?**

Batch mode — less interactive prompts, friendlier for CI logs

**Why verify instead of casual install on every push?**

Proves package + checks without writing into every agent’s ~/.m2 unless the pipeline intentionally installs

**Preferred CI-style command for this bootcamp**

mvn -B verify

Teammates and CI should reproduce the build with mvn -B verify.

