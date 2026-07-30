# Code smells — Lab 12

Catalog **≥8** smells from the messy baseline (`doStuff`). Tie each to CRM impact (CUS-1001).

| # | Smell | Location                                                                  | Impact on CUS-1001                                                                                           |
| - | ----- |---------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| 1 | Poor naming (`doStuff`, `data`) | String a, b, c, d, e or get(String id)                                    | Can pass in the wrong arguments or slow down development                                                     |
| 2 | Raw types | List data                                                                 | could cause ClassCastException                                                                               |
| 3 | Long method / mixed responsibilities | doStuff creates and more (If the person is name has "UPDATE" it updates?) | Could mutate data accidentally. Also if the data is                                                          |
| 4 | Stringly-typed status | the insane else ifs in doStuff                                            | if status is "Active" instead of "ACTIVE", status will be set to "PROSPECT"                                  |
| 5 | Incorrect equality (`==`) | id.getCustomerId == id                                                    | CUS-1001 will never be found                                                                                 |
| 6 | Null as control flow | returning null in doStuff (it means different stuff)                      | the caller can't tell if it got back null becaues CUS-1001 is taken or because something else went wrong.    |
| 7 | Side-effect logging | System.out.println() short messages                                       | Makes the code impossible to test, doesn't even say which Id is causing the output or anything. Very unclear |
| 8 | Magic `"UPDATE"` behavior | name containing UPDATE triggers a status update                           | CUS-1001 might be a company account called UPDATE SYSTEMS or something and it would trigger incorrectly      |
