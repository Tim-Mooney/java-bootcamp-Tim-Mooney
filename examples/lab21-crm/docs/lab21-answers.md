# Which design decision most affected correctness (readiness group vs single health blob)?
- Splitting readiness and liveness. A process can be unready and live.
# What evidence proves create traffic is observable?
- The logging 
# Which failure was hardest to diagnose?
- failure count was staying at 0 for rejecting blank name because of business logic so it was hard.