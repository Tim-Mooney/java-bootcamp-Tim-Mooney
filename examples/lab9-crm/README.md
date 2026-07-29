## CI note (preview — pipelines deepen in later modules)

Preferred verify command on agents:

    mvn -B verify

`-B` is batch mode (non-interactive). Prefer `verify` over `install` on CI
unless your pipeline intentionally publishes to an artifact repository.
Never deploy snapshots from a developer laptop without agreement.

Artifact coordinates: com.northstar:customer-service:0.1.0-SNAPSHOT
Sample customer IDs (docs only): CUS-1001, CUS-1002
Correlation ID (logs later): lab-request-001