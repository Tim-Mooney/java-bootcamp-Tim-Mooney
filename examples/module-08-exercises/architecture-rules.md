
| Dependency | Decision      | Why                                            |
| ---------- |---------------|------------------------------------------------|
| controller → service | acceptable    | this follows the flow below                    |
| service → repository | acceptable    | this follows the flow below                    |
| repository → entity | acceptable    | this follows the flow below                    |
| entity → controller | problematic   | couples entity to controller.                  |
| repository → controller | problematic   | same problem, couples repository to controller |
| service → DTO | needs context | acceptable, but can lead to transport leakage  |
| DTO → repository | problematic   | dto should just transport data, nothing more   |

controller -> service -> repository -> entity/database


Higher-level request handling may call inward services and repositories.
Domain/entity and repository packages must not import controller classes.
