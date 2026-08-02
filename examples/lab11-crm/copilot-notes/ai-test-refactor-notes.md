# AI test/refactor notes — Lab 11

## lab11-001 — generated exploratory test
- Accepted, no trivial assertions.
- Notes: This really isn't bad. It tests findByStatus. I think I passed in the current CustomerServiceTest.java on accident so it had some better context.
- prompt: add one more test to CustomerServiceTest.java
```azure
@Test
    void findByStatusReturnsMatchingCustomers() {
        Customer c1 = new Customer("CUS-1001", "Amina Khan", "amina@example.com", "555-1234", CustomerStatus.ACTIVE, now);
        Customer c2 = new Customer("CUS-1002", "Ravi Singh", "ravi@example.com", "555-5678", CustomerStatus.PROSPECT, now);
        Customer c3 = new Customer("CUS-1003", "Maria Garcia", "maria@example.com", "555-9999", CustomerStatus.ACTIVE, now);
        
        service.addCustomer(c1);
        service.addCustomer(c2);
        service.addCustomer(c3);
        
        var activeCustomers = service.findByStatus(CustomerStatus.ACTIVE);
        
        assertEquals(2, activeCustomers.size(), "Should find 2 ACTIVE customers");
        assertTrue(activeCustomers.stream().anyMatch(c -> c.getCustomerId().equals("CUS-1001")), "Should contain CUS-1001");
        assertTrue(activeCustomers.stream().anyMatch(c -> c.getCustomerId().equals("CUS-1003")), "Should contain CUS-1003");
    }
```

## lab11-002 — CustomerServiceTest
- Notes: If the helper exists, might as well use it. This looks good to me.
- prompt: Review CustomerService for code smells: duplicated logic, long methods,
  unclear names. Suggest one specific refactor.

  replaced
```azure
validateCustomerId(customerId);
        Customer customer = findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("No such customer: " + customerId));
```
with
```azure
Customer customer = findByIdOrThrow(customerId, "No such customer: " + customerId);
```


## lab11-003 — CustomerNotifier extract + Mockito
- Notes:

| Method                         | Covered | Notes                                                                                        |
|--------------------------------|---------|----------------------------------------------------------------------------------------------|
| equals(Object o)               | no      | Could just make 2 Customers with the same ID and assertEquals but its really basic. No need. |
| hashCode()                     | no      | It uses .hash() so its basically like testing a setter or getter. No real need.              |
| toString()                     | no      | Again, toString doesn't have edge cases or anything. Its fine.                               |
| addCustomer(Customer customer) | yes     | addCustomerStoresNewCustomer, addCustomerRejectsDuplicateId                                  |
|findByCustomerId(String customerId)| yes     | addCustomerStoresNewCustomer, updateStatusChangesCustomerStatus                              |
|findByStatus(CustomerStatus status)| yes     | findByStatusReturnsMatchingCustomers                                                         |
|updateCustomerStatus(String customerId, CustomerStatus newStatus)| partial | updateStatusChangesCustomerStatus, CustomerNotifierMockTest. Not tested thoroughly (no ID)   |
|listAll()| yes     | addCustomerStoresNewCustomer                                                                 |
|validateCustomerId(String customerId)| partial | addCustomer, updateStatus use this but its not called in the tests                           |
|findByIdOrThrow(String customerId, String errorMessage)| partial | Never entered the throw path.                                                                |



## lab11-004 — coverage gaps / acceptance guidelines
Acceptance guidelines for AI-generated tests and refactors:
1. Every assertion must be able to fail — if I can't describe an input that
   breaks it, it isn't a real test.
2. Every refactor must be backed by a passing test suite run before and after.
3. No accepted suggestion may introduce a dependency not already in pom.xml.
4. I can explain, without re-reading Copilot's explanation, why the code
   is correct.
5. Coverage gaps are documented, not silently ignored.

