failure 2: add customer is never reached because validator catches the illegal argument exception
failure 3: duplicate is not caught by @NotBlank, it is a business rule violation so it throws an illegal state.
failure 4: in my case I made an invalid email get created by removing validation which could be dangerous later.

**Which design decision most affected correctness?**

Validating the CustomerRequestDTO before calling service.addCustomer — rather than validating inside the service or not at all — is what actually enforces the DTO boundary.

**What evidence proves the implementation works?**

The Main run producing two clean CustomerResponseDTO prints for CUS-1001/CUS-1002, paired with the invalid-email attempt failing with lab-request-001 in the message and never reaching addCustomer; plus the automated messageContainsCorrelationId test asserting that same behavior outside of eyeballed console output.

**Which failure was hardest to diagnose?**

Skipping validate. The data was invalid, but never checked. This allowed a bad email to be stored as a good one quietly.
