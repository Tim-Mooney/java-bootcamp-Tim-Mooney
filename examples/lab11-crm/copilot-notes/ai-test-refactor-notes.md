# AI test/refactor notes — Lab 11

## lab11-001 — generated exploratory test
- Accepted / rejected trivial assertion? 
- Notes:

## lab11-002 — CustomerServiceTest
- Notes: 
-prompt: add one more test to CustomerServiceTest.java
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

This really isn't bad. It tests findByStatus. I think I passed in the current CustomerServiceTest.java so it had some better context.

## lab11-003 — CustomerNotifier extract + Mockito
- Notes:

| Method                         |Covered|Notes|
|--------------------------------|-------|-----|
| equals(Object o)               | | |
| hashCode()                     | | |
| toString()                     | | |
| addCustomer(Customer customer) | | |
|findByCustomerId(String customerId)| | |
|findByStatus(CustomerStatus status)| | |
|updateCustomerStatus(String customerId, CustomerStatus newStatus)| | |
|listAll()| | |
|validateCustomerId(String customerId)| | |
|findByIdOrThrow(String customerId, String errorMessage)| | |



## lab11-004 — coverage gaps / acceptance guidelines
- Notes:

##lab11-006 - 
- Notes: prompt :Review CustomerService for code smells: duplicated logic, long methods,
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
