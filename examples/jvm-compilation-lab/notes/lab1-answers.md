**What is the difference between HelloWorld.java and HelloWorld.class?**

HelloWorld.java is the code written in java and can be read and edited by a human. HelloWorld.class is the bytecode and cannot be understood by a human. Compiling the .java file creates the .class file. 

![](/Users/timmooney/Desktop/Screenshot 2026-07-21 at 10.26.00 AM.png)


| Code element | Memory area |
| ------------ | ----------- |
| Locals `x`, `y`, `sum` in `main` | Stack (locals in `main` frame) |
| Parameters `a`, `b` and local `result` in `add` | Stack (`add` frame) |
| Method call `add(x, y)` | New stack frame pushed, then popped on return |
| Class metadata for `Calculator` | Metaspace (simplified course term) |
| Temporary `String` for `"Sum = " + sum` | Heap (String / builder intermediates) |

Calling add() pushes a frame onto the stack and when add() returns it pops the frame from the stack

![](/screenshots/Screenshot 2026-07-21 at 11.12.40 AM.png)

![](/Users/timmooney/Desktop/Screenshot 2026-07-21 at 10.45.41 AM.png)

If I add a 0 to MemoryDemo.java and make the heap 64mb it crashes with a OOM error but if I run it without limiting the heap size it runs. This demonstrates the heap size changing.

size_t InitialHeapSize                          = 268435456                                 {product} {ergonomic}\
size_t MaxHeapSize                              = 4294967296                                {product} {ergonomic}\
size_t SoftMaxHeapSize                          = 4294967296                             {manageable} {ergonomic}\
bool UseG1GC                                  = true                                      {product} {ergonomic}

![](/Users/timmooney/Desktop/Screenshot 2026-07-21 at 11.01.46 AM.png)