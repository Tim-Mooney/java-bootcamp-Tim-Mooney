# Lab 21 — monitoring report

## Probes

| Probe | Expected when ready | Expected when lab toggle down |
| ----- | ------------------- | ----------------------------- |
| liveness | UP | UP |
| readiness | UP | OUT_OF_SERVICE / DOWN |

## Metrics

- `crm.customer.create` tag `result`
- `crm.customer.get` tag `result`
- Never tag `customerId` or correlation id

## Production note

Lab exposure of health+metrics+info is **not** production-safe — restrict endpoints later.

## TODO

Paste curl evidence for health / liveness / readiness / metrics after smoke test.


## Live but not ready vs dead
An app that is live but not ready is responding to health checks, but it's not ready because some dependency (schema migration for example)
hasn't finished. It is healthy and becomes ready when the dependencies finish. 
A dead process has crashed or become unresponsive, so it should just be restarted. 

# CRM Monitoring Report (Lab 21)
- Health: /actuator/health, /liveness, /readiness
- Metrics: crm.customer.create{result}, crm.customer.get.latency
- Example traffic: POST CUS-2101, GET CUS-1001, corr=lab-request-001
- Alert idea: create failure ratio > 5% for 5 minutes
- Production: do not expose unrestricted Actuator on the public internet
- Cards: IDs in logs (Lab 20); aggregates in metrics (this lab)