Failure 2: I left the test in the test file. 

# Which design decision most affected correctness (filter-owned MDC vs service-owned)?
- Filter owning MDC.clear() protects against requests leaking into each other.
# What evidence proves support can search a request?
- The correlation ID changes between requests.
# Which failure was hardest to diagnose?
- The duration. The operations are really fast so I thought I was measuring wrong.