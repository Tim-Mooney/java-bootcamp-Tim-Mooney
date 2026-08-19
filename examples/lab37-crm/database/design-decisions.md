# Lab 37 — Design decisions

## public_id vs surrogate key

why both `customer_id` and `public_id` (CUS-…)?
Customer id is the internal id used for joins and isn't seen by anyone outside
public id is the id the customer knows and can use to get whatever they need


## Constraints

which CHECKs/UNIQUEs/FKs protect CRM integrity?
Unique customer id makes accounts unique and two accounts from sharing the same id
unique email makes accounts unique again and makes sure one email goes to one account
unique account number same as customer id
status check makes sure the status is a valid status
fk on customer id makes sure the account actually relates to a real customer that exists

