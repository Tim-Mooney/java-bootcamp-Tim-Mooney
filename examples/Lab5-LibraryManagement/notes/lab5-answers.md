```azure

=====================================
Library Management System
=====================================
1 Add Book
2 Register Member
3 Display Books
4 Display Members
5 Search Book
6 Borrow Book
7 Return Book
8 Display Borrowed Books
9 Sort Books
10 Reports
11 Exit
12 Display Available Books
13 Category Insights (TreeSet/TreeMap)
14 Performance Comparison (Bonus)
15 Borrow History (Bonus)
16 Top 5 Borrowed Books (Bonus)
17 Export Report (Bonus)
Choice : 1
----------------------------------
Book ID : 101
Title : Java Fundamentals
Author : James Gosling
Category : Programming
Price : 55
Book Added Successfully

=====================================
Library Management System
=====================================
1 Add Book
2 Register Member
3 Display Books
4 Display Members
5 Search Book
6 Borrow Book
7 Return Book
8 Display Borrowed Books
9 Sort Books
10 Reports
11 Exit
12 Display Available Books
13 Category Insights (TreeSet/TreeMap)
14 Performance Comparison (Bonus)
15 Borrow History (Bonus)
16 Top 5 Borrowed Books (Bonus)
17 Export Report (Bonus)
Choice : 2
----------------------------------
Member ID : 1
Name : John
Email : john@example.com
Phone : 1234567890
Member Registered Successfully

=====================================
Library Management System
=====================================
1 Add Book
2 Register Member
3 Display Books
4 Display Members
5 Search Book
6 Borrow Book
7 Return Book
8 Display Borrowed Books
9 Sort Books
10 Reports
11 Exit
12 Display Available Books
13 Category Insights (TreeSet/TreeMap)
14 Performance Comparison (Bonus)
15 Borrow History (Bonus)
16 Top 5 Borrowed Books (Bonus)
17 Export Report (Bonus)
Choice : 6
----------------------------------
Book ID: 101
Member ID: 1
Successfully borrowed a book!

=====================================
Library Management System
=====================================
1 Add Book
2 Register Member
3 Display Books
4 Display Members
5 Search Book
6 Borrow Book
7 Return Book
8 Display Borrowed Books
9 Sort Books
10 Reports
11 Exit
12 Display Available Books
13 Category Insights (TreeSet/TreeMap)
14 Performance Comparison (Bonus)
15 Borrow History (Bonus)
16 Top 5 Borrowed Books (Bonus)
17 Export Report (Bonus)
Choice : 10
----------------------------------
Books: 1
Borrowed: 1
Available: 0
Members: 1
Most popular category: Programming

=====================================
Library Management System
=====================================
1 Add Book
2 Register Member
3 Display Books
4 Display Members
5 Search Book
6 Borrow Book
7 Return Book
8 Display Borrowed Books
9 Sort Books
10 Reports
11 Exit
12 Display Available Books
13 Category Insights (TreeSet/TreeMap)
14 Performance Comparison (Bonus)
15 Borrow History (Bonus)
16 Top 5 Borrowed Books (Bonus)
17 Export Report (Bonus)
Choice : 11
----------------------------------
Thank You
```

**If you don't need sorted order, it's probably cheaper to avoid it**

It's like buying a riding lawn mower for an apartment.

ArrayList<Book> books - Mostly will be read from, which is where ArrayLists are strong. ArrayLists have fast access by index O(1). 

ArrayList<Member> members - Same justification as above.

HashSet<String> bookIds - IDs must be unique, and these are only used to see if an ID is already taken.
Also, order doesn't matter, so we can use a HashSet instead of an ordered collection. Gives O(1) avg on .contains() which is all they do other than be populated (bookIds can be removed from but same thing)

HashSet<String> memberIds - Same reason as above.

HashMap<String, String> borrowRecords - HashMap has fast lookup by key which is bookId and that's what the program looks up by. Also, order doesn't matter so we can get away with it.

TreeSet<String> categories - Needs to be unique by nature of categories and needs to be ordered for category insights option. TreeSet is both unique and ordered.

TreeMap<String, Integer> categoryBookCount - Again, needs to be in order for category insights. It needs to store a key and value pair and be in order so TreeMap is natural.

ArrayList<BorrowRecord> borrowHistory - Only added to and never deleted from. Maintains insertion order because its only ever .add()ed to. Quick access by index when it is needed. 

HashMap<String, Integer> borrowFrequency - Doesn't need to be sorted. Stores bookIds (key) and how many times they are borrowed (value).


**When choose List over Set?**

Set if you need unique, List if you need insertion order, index access, and are ok with duplicates.

**Why HashSet before inserting a book ID?**

HashSet is fast for .contains and that is what needs to be checked before adding a new book ID.

**Why a Map for “currently borrowed” vs only a boolean?**

A map with keys and values is better than a boolean because it applies to individual books.

**HashMap vs TreeMap in this lab?**

TreeMap is sorted which is needed for some maps. Hashmap is not sorted.

**Comparable vs Comparator for books?**

Comparable says something can be compared, comparator is a way to compare the books.

**Which iteration style would you use most in production—and why?**

Probably streams and enhanced for loops. Streams are lazy and efficient and enhanced for loops are easy to read and implement.

**CRM: which collection for customer list / unique emails / id→customer lookup?**

customer list - List
unique emails - Set
id→customer - Map
