# Northstar CRM Coding Standards (Lab 8)

## Layers

- controller: transport / API mapping only
- service: business rules
- repository: persistence
- entity: domain model
- dto: request/response contracts
- config: wiring
- exception: domain and API failures

## Hard rules

- Services must not depend on controllers.
- Entities must not carry HTTP or SOAP types.
- Repositories must not import controllers.
- No production passwords or API keys in source.
- Prefer CUS-#### for stable customer identities in examples.

## Dependency direction

controller -> service -> repository -> entity
controller -> dto
service    -> dto, entity, exception
repository -> entity
entity     -> (nothing in other CRM layers)
config     -> (wiring only; later may reference beans)


## Naming conventions

-Classes named for the layer they belong to CustomerController - controller, etc.
-DTOs named for their purpose CustomerRequest, CustomerResponse.
-Packages named with reverse domain notation com.northstar.crm.LAYER

## DTO vs entity separation

-Customer (Entity) is the internal/domain model. It is what repository stores and service operates on.
-CustomerRequest / CustomerResponse (DTOs) are the external contract. They are what controller accepts and returns.
-These are never the same object, and one must not leak into the other's layer:
-CustomerResponse must not expose internal storage details.
-Customer must not carry request-only fields.
-Mapping between entity and dto is a service-layer responsibility. 
-Controller should never construct a Customer entity directly, and repository should never see a CustomerRequest/CustomerResponse.

## Exception handling

-Domain failures live in the exception package (e.g. CustomerNotFoundException).
-CustomerNotFoundException(customerId) constructs a clear message, e.g. Customer not found: CUS-1002 — always include the customer ID, never a bare "not found."

## Tooling
JDK 21 — required. Confirm with java -version before compiling.
Maven 3.9+ — standard build tool for this project; use mvn clean compile to verify the build, and mvn clean before capturing cleanup evidence.
