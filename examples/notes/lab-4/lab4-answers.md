```
===== Performance Measurement =====

===== JVM Memory Report: Start =====
Total Memory : 130 MB
Free Memory  : 127 MB
Used Memory  : 2 MB
Max Memory   : 512 MB
-----------------------------

Objects      Used Memory    Execution Time    
--------------------------------------------------
10           0MB            1838667ms         
100          0MB            51333ms           
1000         0MB            607916ms          
100000       10MB           8647375ms         
1000000      107MB          49681959ms        

Additional measurements:
Loop execution (10M iterations) : 14017792 ms | sum = 49999995000000.
int[1,000,000] allocation: 3393959 ms

===== JVM Memory Report: Before Large byte[] =====
Total Memory : 128 MB
Free Memory  : 122 MB
Used Memory  : 5 MB
Max Memory   : 512 MB
-----------------------------

===== JVM Memory Report: After Large byte[] =====
Total Memory : 128 MB
Free Memory  : 111 MB
Used Memory  : 16 MB
Max Memory   : 512 MB
-----------------------------

===== JVM Memory Report: After Releasing =====
Total Memory : 128 MB
Free Memory  : 126 MB
Used Memory  : 1 MB
Max Memory   : 512 MB
-----------------------------
```



**STACK EXAMPLE**

```
===== Stack Memory Demonstration =====
Call chain: main() -> methodA() -> methodB() -> methodC()

main() frame
  Primitive on stack : mainCounter = 1
  Reference on stack : mainLabel -> "main-frame" (String object on heap)
  Reference on stack : person -> Person{name='Main User', age=30}
  Identity hash code : 1300109446

methodA() frame
  Primitive on stack : localA = 20
  Reference on stack : labelA -> "frame-A" (String object on heap)
  Reference on stack : person -> Person{name='Alice', age=25}
  Identity hash code : 1414644648

methodB() frame
  Primitive on stack : localB = 25
  Reference on stack : labelB -> "frame-B" (String object on heap)
  Reference on stack : person -> Person{name='Bob', age=28}
  Identity hash code : 640070680

methodC() frame
  Primitive on stack : localC = 22
  Reference on stack : labelC -> "frame-C" (String object on heap)
  Reference on stack : person -> Person{name='Carol', age=32}
  Identity hash code : 1510467688

Stack frame stores:
- Primitive values directly (localC = 22)
- Object references (personC reference on stack, object on heap)
- Return address to methodB()

Back in main() - methodC() frame has been removed from the stack.
  Primitive on stack : mainCounter = 1
  Reference on stack : mainLabel -> "main-frame" (String object on heap)
  Reference on stack : person -> Person{name='Main User', age=30}
  Identity hash code : 1300109446
  ```

**HEAP EXAMPLE**
```azure
===== Heap Memory Demonstration =====

===== JVM Memory Report: Before Allocation =====
Total Memory : 258 MB
Free Memory  : 255 MB
Used Memory  : 2 MB
Max Memory   : 4096 MB
-----------------------------
Objects created on the heap:

Reference (stack) : student
Object (heap)     : Student{name='John'}
Identity hash     : 681842940

Reference (stack) : employee
Object (heap)     : Employee{id=101}
Identity hash     : 41359092

Reference (stack) : customer
Object (heap)     : Customer{customerId='C-5001'}
Identity hash     : 149928006

Reference (stack) : book
Object (heap)     : Book{title='Effective Java'}
Identity hash     : 713338599

===== JVM Memory Report: After Allocation =====
Total Memory : 258 MB
Free Memory  : 254 MB
Used Memory  : 3 MB
Max Memory   : 4096 MB
-----------------------------
Observation:
- References (student, employee, ...) live on the stack
- Actual objects live on the heap
- identityHashCode() helps distinguish object identity
```

**OBJECT LIFE CYCLE**
```azure
===== Object Lifecycle Demonstration =====
Step 1: Create object
Created -> Person{name='Diana', age=27}
Identity hash : 1872034366

Step 2: Use object
Name : Diana
Age  : 27

Step 3: Hold reference
secondReference points to same object : true

Step 4: Remove references
person reference removed
secondReference removed - object is now unreachable

Step 5: Eligible for Garbage Collection

===== JVM Memory Report: Before GC =====
Total Memory : 258 MB
Free Memory  : 254 MB
Used Memory  : 3 MB
Max Memory   : 4096 MB
-----------------------------

===== JVM Memory Report: After GC =====
Total Memory : 20 MB
Free Memory  : 18 MB
Used Memory  : 1 MB
Max Memory   : 4096 MB
-----------------------------

An object becomes eligible for GC when no live thread can reach it.
```

Leak is adding employees to a private static final list, so the list will never be collected.
The garbage collector will think that they are still ineligible.
Fix adds to a local arraylist and then clears the arraylist's references to the object, then removes the reference to the list itself.
Now, the garbage collector can see that all of those objects are unreachable and should collect them.