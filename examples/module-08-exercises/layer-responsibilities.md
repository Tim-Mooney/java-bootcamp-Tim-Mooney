
| Task | Layer      |
| ---- |------------|
| Accept future create-customer input | controller |
| Reject blank customer name | service    |
| Find customer by ID | repository |
| Represent customer ID/name/status | dto        |
| Represent create request fields | config     |
| Define customer-not-found failure | exception  |
| Wire application objects later | config     |

boundaries keep files separate and make the project easier to work on/ become familiar with. Boundaries help with security and working with large teams.
