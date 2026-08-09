# Lab 24 — PayloadRoot Skeleton

## Class annotation
@Endpoint class CustomerEndpoint

## @PayloadRoot localPart
@PayloadRoot(namespace = NAMESPACE, localPart = "GetCustomerRequest")

## Method inputs/outputs
method getCustomer(@RequestPayload GetCustomerRequest req)

## Delegation line (words)
getCustomer → map → customerService.get(...) → map response

NAMESPACE must match customer.xsd targetNamespace.


## Scope
Pre-lab only.