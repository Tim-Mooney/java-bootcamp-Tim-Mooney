compile with javac -d out src/com/academy/student/*.java
run with java -cp out com.academy.student.Main

The package keeps the files separate and maintains a clean file structure.

Using one scanner prevents multiple scanners being open at once. Passing the scanner around where it is needed is best.

Using a fixed size array is fine because it should never be bigger than MAX_STUDENTS. MAX_STUDENTS is the most I ever want in a course and studentCount keeps track of the number of students and helps with insertion.