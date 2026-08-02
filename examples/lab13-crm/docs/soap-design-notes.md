# SOAP design notes — Lab 13

## TODO
**1. Contract-first vs code-first for partners**

 - Contract-first is preferred for partners, but code-first is acceptable for internal services. Use contract-first for external-facing endpoints to ensure clear communication and compatibility.

**2. Document/literal choice**

 - Document/literal is preferred for SOAP messages as it provides better interoperability and clarity in message structure. It allows for more complex data types and is generally easier to work with in terms of validation and schema definition.

**3. Correlation placement (`lab-request-001`)**

 - CorrelationId is placed as an optional element in the request body (e.g. inside getCustomerRequest, createCustomerRequest), not in a SOAP header.

**4. Fault shapes: not-found vs validation**

 -Both are used.
 fault-customerNotFound.xml — errorCode = CUSTOMER_NOT_FOUND. The request was well-formed and schema-valid; the referenced customerId (CUS-9999) simply doesn't exist. This is a business-rule failure a future service would raise after successful schema validation.
 fault-validation.xml — errorCode = NAME-BLANK. The request itself violates an input constraint (blank fullName). This is a failure a JAXB/schema layer or a validating endpoint would raise before any business logic runs.

**5. What Lab 24 will host vs what stays static here**

 -Lab 24maps these ideas to Spring-WS faults—do not implement endpoints now. Cross-walk UpdateCustomer ↔ Lab 12 updateStatus briefly.


