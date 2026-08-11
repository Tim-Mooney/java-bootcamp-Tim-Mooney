Failure 1: Posting SOAP when nobody is listening doesn't do anything
Failure 3: it faulted immediately

# Which design decision most affected correctness (contract-first)?
- Using the XSD's targetNamespace and element names as the literal contract that @PayloadRoot has to match
# What evidence proves SOAP and REST share rules?
- The UpdateStatus → REST GET sequence: SOAP UpdateStatus flipped CUS-1001 to CHURNED, and the immediately following REST GET /api/customers/CUS-1001 returned that same CHURNED status — with no separate write on the REST side
# Which failure was hardest to diagnose (payload root vs WSS)?
- XML without security. It was silently broken for the timed path.