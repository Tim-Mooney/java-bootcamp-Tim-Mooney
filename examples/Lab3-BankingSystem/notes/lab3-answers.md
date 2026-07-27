**Why should Account be abstract rather than a concrete empty type?**

There are multiple types of accounts. Different accounts need to do different things, so leave it up to specific  account types. No account should just be an account.

**Where does dynamic dispatch show up when you call displayAccount() on Account[]?**

Accounts is an array of different account types. displayAccount changes based on account type.

**How does Printable differ from extending a base class?**

Printable is an interface, so implimenting printable means it can be printed.

**What would break if Main owned all arrays instead of BankService?**

SOLID principles. Main would need to have pointers and everything and Main would just be the class doing almost everything.

**How do today’s Customer/Account patterns prepare you for later CRM entity design without building Spring here?**

It teaches about thinking about connections and layers. How different entities interact.