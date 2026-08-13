Failure 3: I renamed the connect-timeout-ms so it reverted back to 2000.
Failure 5: If default contained some secrets, the secrets could easily leak.

# Which design decision most affected correctness — YAML split or typed binding?
- The YAML split made different profiles but the typed binding made catching mistakes early easier. Probably typed binding for things like failure 3.
# What evidence proves prod cannot start with blank credentials?
- Running `mvn spring-boot:run -Dspring-boot.run.profiles=prod` with no DB_USERNAME/
  DB_PASSWORD/NORTHSTAR_API_KEY set produces `The following 1 profile is active: "prod"`
  followed by `APPLICATION FAILED TO START`
# Which failure was hardest (missing prop, wrong profile, override confusion)?
- Renaming the key, it reverted to 2000 so if it wasn't a test it would have been hard to notice.