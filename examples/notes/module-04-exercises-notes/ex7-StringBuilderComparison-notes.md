String: 50000 chars, 119.142 ms
StringBuilder: 50000 chars, 1.311 ms

String: 50000 chars, 157.911 ms
StringBuilder: 50000 chars, 1.165 ms

String: 50000 chars, 99.906 ms
StringBuilder: 50000 chars, 1.317 ms

Strings are immutable, they are objects that cannot change and appending to them does not change them, it creates a copy with the change.

Stringbuilder keeps the same object and changes.

Use StringBuilder when constructing text repeatedly in a loop. Ordinary + remains readable and appropriate for a small, fixed expression.