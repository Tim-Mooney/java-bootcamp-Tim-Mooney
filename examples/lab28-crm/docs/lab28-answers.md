## COMMANDS USED FOR STEP 7 ##

```bash
AGENT_TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"agent1","password":"agent1"}' | jq -r .accessToken)

ADMIN_TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin1","password":"admin1"}' | jq -r .accessToken)

echo "agent1 -> customers:"
curl -s -o /dev/null -w "%{http_code}\n" http://localhost:8080/api/customers/CUS-1001 \
  -H "Authorization: Bearer $AGENT_TOKEN"

echo "agent1 -> admin/ping:"
curl -s -o /dev/null -w "%{http_code}\n" http://localhost:8080/api/admin/ping \
  -H "Authorization: Bearer $AGENT_TOKEN"

echo "admin1 -> customers:"
curl -s -o /dev/null -w "%{http_code}\n" http://localhost:8080/api/customers/CUS-1001 \
  -H "Authorization: Bearer $ADMIN_TOKEN"

echo "admin1 -> admin/ping:"
curl -s -o /dev/null -w "%{http_code}\n" http://localhost:8080/api/admin/ping \
  -H "Authorization: Bearer $ADMIN_TOKEN"
```

# Which design decision most affected correctness (stateless token vs session)?
- Stateless tokens, made the filter the decider on identity.
# What evidence proves role separation works?
- the test of agent and admin where admin gets everything and agent gets only what it should get.
# Which failure was hardest to diagnose (401 vs 403 vs filter order)?
- 401 vs 403, I didn't have it set up at first