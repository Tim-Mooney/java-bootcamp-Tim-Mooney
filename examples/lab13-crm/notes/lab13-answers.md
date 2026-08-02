failure 2: Still well formed, but broken.
failure 3: create is unsafe to retry because it may create duplicates. Update is safe to retry because it is idempotent.
failure 4: 1 second. I searched for it, but the binding is in the same place so just go to the bottom.


**Which design decision most affected partner usability?**

- Putting correlationId as an optional field directly in the request/response body (rather than a SOAP header) probably has the biggest impact. 

**What evidence proves the contract is implementable in Lab 24?**

- Every operation in portType has a matching <binding> entry with a document/literal style and a real soapAction, which is exactly what Spring-WS's @Endpoint + MessageDispatcherServlet setup expects

**Which failure was hardest to diagnose (namespace vs element name)?**

- Empty customerId because it is still well formed, it just won't work.