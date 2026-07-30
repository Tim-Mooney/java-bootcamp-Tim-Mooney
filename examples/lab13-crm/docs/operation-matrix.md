# Operation matrix — Lab 13

| Operation | Purpose | Key inputs                                     | Key outputs          |
| --------- | ------- |------------------------------------------------|----------------------|
| CreateCustomer | Register a new CRM customer | fullName, email, phone, status                 | Customer, customerId |
| UpdateCustomer | Change mutable fields / status | customerId, optional fields (email, name, etc) | Customer             |
| GetCustomer | Fetch one customer by ID | customerId                                     | Customer             |

Namespace: `http://northstar.com/crm/customer`  
Endpoint placeholder: `http://localhost:8080/ws` (**not live** in this lab)
