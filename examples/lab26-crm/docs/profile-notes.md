# Lab 26 — Profile / precedence notes

# which source won in your override experiment (CLI > env > profile YAML > base)?
- CLI won the experiment. CLI overrides everything, in this case the environment variable.
# evidence that prod refuses missing secrets
- I ran unset DB_USERNAME DB_PASSWORD NORTHSTAR_API_KEY SPRING_PROFILES_ACTIVE
 then mvn spring-boot:run -Dspring-boot.run.profiles=prod 2>&1 | tee prod-fail.log
 and saw that it failed to start.
