# Before / after — Lab 12

## Before
- API: `doStuff` / `get`
- Failures return null; `==` on IDs

## After
- API: `createCustomer` / `getCustomer` / `updateStatus` / `requireNonBlank` / `isUniqueId` / `requireExisting` / `correlationId`
- Exceptions for unknown/duplicate
- Typed `Map<String, Customer>`; no `doStuff`

  65 src/main/java/com/northstar/crm/service/CustomerService.java
  64 src/main/java/com/northstar/crm/service/CustomerService.before.java.txt
  129 total


| # | Smell                                | fix                                                |                                                                                                       
|---|--------------------------------------|----------------------------------------------------|
| 1 | Poor naming (`doStuff`, `data`)      | renaming methods                                   |                                                      
| 2 | Raw types                            | HashMap<String, Customer>                          |
| 3 | Long method / mixed responsibilities | making more methods and splitting responsibilities |                                                         
| 4 | Stringly-typed status                | status is a parameter                              |                                  
| 5 | Incorrect equality (`==`)            | .equals                                            |                                                                            
| 6 | Null as control flow                 | use exceptions                                     | 
| 7 | Side-effect logging                  | System.out.println() helpful messages              | 
| 8 | Magic `"UPDATE"` behavior            | remove magic behavior entirely                     | 

