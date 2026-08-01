# AI review notes — Lab 10

**Inline completion vs Copilot Chat — when you use each.**

Copilot chat is better for multi-step reasoning, e.g. “write a customer class with fields, types, and a method to add a customer.” 
Inline completion is better for single-step code generation, e.g. “Create customer object with name "Amina”"

**Why a strong prompt (fields, types, no Spring/JPA) beats “write a customer class.”**

A strong prompt is more likely to produce a correct, complete, and secure output. 
A weak prompt may produce code that is incomplete, insecure, or uses frameworks/libraries that are not allowed in the lab.
AI needs to be told what to do, and what not to do, in order to produce a correct and secure output.

**Trust boundary: AI suggests; you own what merges / touches customer data.**

AI is a tool to help you write code, but you are responsible for reviewing and testing the code before merging it into your project.

**Phantom classpath risk: reject annotations/libraries not in this POM (e.g. @Entity).**

If AI suggests code that uses libraries or annotations not included in your project’s POM, reject it.
If I don't know what a library or annotation does, I will research it before accepting or rejecting it.

## lab10-001 — weak vs strong (entity)
- Date: 7/30/2026
- Weak prompt used: customer class (inline completion)
- Output summary: CustomerWeak.java in copilot-comment-prompts. Has some fields and a constructor. A comment to make getters.
- Strong prompt used: (Inline again) Java entity class Customer in package com.northstar.crm.entity representing a Northstar CRM customer. Fields: customerId (String, format "CUS-1001"), fullName (String), email (String), phone (String), status (CustomerStatus enum: PROSPECT, ACTIVE, SUSPENDED, CLOSED), createdAt (LocalDateTime). No-args constructor, all-args constructor, getters and setters, equals/hashCode based only on customerId, toString.
- Output summary: A better customer class with all requested fields, constructors, getters/setters, equals/hashCode, and toString. No Spring or JPA annotations.
- Decision: accept with edits
- Reason (1 sentence): It wasn't perfectly what I wanted, but it was close enough to accept and then edit.

## lab10-002 — weak vs strong (addCustomer)
- Date: 7/30/2026
- Weak prompt used: add a customer (inline completion)
- Output summary: A method to add a customer, but it was incomplete and did not handle any inputs bad inputs. Never stored it anywhere.
- Strong prompt used: (Inline again) Method addCustomer(Customer customer) on CustomerService: reject if customerId is null/blank, reject if a customer with the same customerId already exists (throw IllegalStateException), otherwise store it in the in-memory list and return it.
- Output summary: A complete addCustomer method that checks for null/blank customerId, checks for duplicates, and stores the customer in an in-memory list.
- Decision: accept
- Reason: It worked as expected and met the requirements of the strong prompt. Normally I think this would be a copilot chat prompt, but I wanted to see if inline completion could handle it.

## lab10-003 — CustomerStatus / Customer scaffold
- Prompt CustomerStatus: (inline) Java enum CustomerStatus in package com.northstar.crm.entity with exactly four constants representing a Northstar CRM customer lifecycle: PROSPECT, ACTIVE, SUSPENDED, CLOSED.
- Rejected JPA? yes
- Notes: It never added the package declaration, so I had to add it manually. The enum itself was correct and complete.
- Decision: accept with edits
- Reason: It was correct, but I had to add the package declaration manually.

## lab10-004 — CustomerService review
- Prompt: In copilot chat
In com.northstar.crm.service, write a plain Java class CustomerService
  (no Spring annotations — this project has no Spring dependency yet).
  It should hold customers in an in-memory List<Customer>. Methods:
  addCustomer(Customer) — reject blank customerId, reject duplicate customerId
  with IllegalStateException, otherwise store and return the customer;
  findByCustomerId(String) — return Optional<Customer>;
  findByStatus(CustomerStatus) — return List<Customer>;
  updateStatus(String customerId, CustomerStatus newStatus) — throw
  IllegalArgumentException if the customer does not exist, otherwise
  update and return it;
  listAll() — return an unmodifiable copy of all customers.
- Notes: The output was good and matched the example, but the listAll method used an unmodifiable list which I
wasn't entirely confident in, so I edited it.
- Decision: accept with edits
- Reason: The output was good and matched the example, but the listAll method was iffy so I changed it.

**What real customer data did you avoid typing into Chat, and what did you use instead (CUS-1001 / CUS-1002)?**

I don't really have any real customer data, but I wouldn't type any actual
customer names or data into chat. I used CUS-1001 and CUS-1002 as placeholders for customer IDs,
and Amina Khan and Ravi Singh as placeholder customer names.

**If Copilot suggests a block that looks copied verbatim from a known library/article, what do you do before accepting?**

I would rewrite the code as my own, borrowing the logic but not copying the code.

**What is your team’s rule for code Copilot generates that you do not fully understand?**

Figure out what it does. You cannot just accept code because it compiles and runs. 
You must understand what is happening in order to ensure it is correct, secure, and maintainable. 
If you do not understand it, research it before accepting it into your codebase.
If you research and understand it and it's correct, use it. Otherwise, reject.


## failure experiments

1. Copilot asked for further explanation of the prompt, which I did not provide. It added a save() method that just changes the createdAt time to now and returns the Customer.
I left it as a comment in Customer.java
2. I turned off copilot and wrote deleteCustomer.
3. Fake prompt: "Edit toString. Output should look like: 'Customer ssn - *fake ssn example*'" the safer version would be "Edit toString. Output should look like: 'Customer id - CUS-1001'"
This is unsafe because it could lead to real customer data being exposed. The generated code may use an example SSN that is a real human.
4. Chat cannot build the entire service layer in one prompt. It is best used for smaller tasks. Writing something like "fill in the getters and setters" would be a bunch of methods and work, but multiple complex methods is too much.