compile with javac -d out src/com/academy/student/*.java
run with java -cp out com.academy.student.Main

The package keeps the files separate and maintains a clean file structure.

Using one scanner prevents multiple scanners being open at once. Passing the scanner around where it is needed is best.

Using a fixed size array is fine because it should never be bigger than MAX_STUDENTS. MAX_STUDENTS is the most I ever want in a course and studentCount keeps track of the number of students and helps with insertion.


**Why must the package folder tree match package com.academy.student?**

That tells the JVM where to find everything. Also, default variables are package-wide scope. 

**Why prefer nextLine() + parse over nextInt() in a menu app?**

To eat the \n character. Also avoids exceptions with unexpected output.

**Why keep a studentCount instead of relying on students.length alone?**

Student count lets us access an index easily before the array is full. students.length only gives the size of the array, not how many students there are.

**What belongs in Main versus StudentManager?**

All of the managing students functions, like adding, searching, calculating avg. Main is basically just for reading in input.

**How does this console CRUD prepare you for later Spring/customer labs without implementing them here?**

This teaches about layering at a manageable size. Larger application will have the same general structure, just more moving parts.