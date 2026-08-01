
```text
controller -> service -> repository -> entity
controller -> dto
service    -> dto, entity, exception
repository -> entity
entity     -> (nothing in other CRM layers)
config     -> (wiring only; later may reference beans)
```

failure experiment 4:
Repository importing controller is a violation of the dependency direction rule. 
The repository layer should not depend on the controller layer, as it breaks the separation of concerns and can lead to tight coupling between layers.


**Which design decision most affected correctness of the skeleton?**

Probably the dependency direction rule, because it is a hard rule that the compiler cannot enforce. 
It requires knowledge of the dependency direction table to diagnose violations, which can lead to subtle bugs if not followed correctly.
Also, without this rule, the skeleton is incorrect but not in an obvious way.

**What evidence proves the layered structure is real, not only aspirational?**

The package structure. The packages are organized according to the layers, so the layers are seperated.

**Which failure was hardest to diagnose (pathing, packages, POM)?**

Changing pom was annoying to do because intelliJ protects me from myself, I had to rename it from finder.
The hardest to actually diagnose was the repository importing controller, because it was not immediately obvious that the dependency direction rule was being violated.
The compiler can't catch that, so it requires knowledge of the dependency direction table.

