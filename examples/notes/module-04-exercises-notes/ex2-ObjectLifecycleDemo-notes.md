first points to the actual object created and alias points to first, which is like a pointer to the object. (I know java doesn't do pointers the same way but this is just how I can put my thoughts into words)

An object is not collectible merely because one reference becomes null.
It becomes GC-eligible only when no live strong-reference path can reach it.
Eligibility does not guarantee immediate collection, and System.gc() is only
a request.

When both variables referencing (pointing to) the object are made null, the object is unreachable and GC eligible.

System.gc() is a request, not a command. 