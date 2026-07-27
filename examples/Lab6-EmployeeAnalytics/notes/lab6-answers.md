```azure
=====================================
Employee Analytics
=====================================
1 Display Employees
2 Employees By Department
3 Salary Report
4 Top Performers
5 Highest Salary
6 Department Statistics
7 Active Employees
8 Dashboard
9 Exit
Choice : 1
----------------------------------
Total Employees : 25
Employee List
E001 | John Smith | IT | $165000 | 12 yrs | Rating 5 | Active
E002 | Alice Johnson | Finance | $152000 | 10 yrs | Rating 5 | Active
E003 | David Lee | Sales | $149000 | 14 yrs | Rating 4 | Active
E004 | Sarah Brown | IT | $141000 | 9 yrs | Rating 5 | Active
E005 | Michael Chen | Marketing | $138000 | 11 yrs | Rating 4 | Active
E006 | Emily Davis | HR | $92000 | 7 yrs | Rating 4 | Active
E007 | Robert Wilson | IT | $118000 | 8 yrs | Rating 4 | Active
E008 | Laura Martinez | Finance | $99000 | 6 yrs | Rating 3 | Active
E009 | James Taylor | Sales | $87000 | 5 yrs | Rating 3 | Active
E010 | Olivia Anderson | Marketing | $76000 | 4 yrs | Rating 4 | Active
E011 | Daniel Thomas | HR | $68000 | 3 yrs | Rating 3 | Active
E012 | Sophia Jackson | IT | $132000 | 13 yrs | Rating 5 | Active
E013 | William White | Finance | $105000 | 9 yrs | Rating 4 | Active
E014 | Ava Harris | Sales | $94000 | 6 yrs | Rating 4 | Active
E015 | Ethan Clark | Marketing | $72000 | 2 yrs | Rating 3 | Active
E016 | Mia Lewis | HR | $61000 | 2 yrs | Rating 2 | Active
E017 | Noah Walker | IT | $98000 | 7 yrs | Rating 4 | Active
E018 | Isabella Hall | Finance | $84000 | 5 yrs | Rating 3 | Active
E019 | Liam Allen | Sales | $58000 | 1 yrs | Rating 2 | Active
E020 | Charlotte Young | Marketing | $54000 | 1 yrs | Rating 3 | Active
E021 | Benjamin King | IT | $124000 | 10 yrs | Rating 4 | Active
E022 | Amelia Wright | HR | $48000 | 1 yrs | Rating 2 | Inactive
E023 | Lucas Scott | Finance | $112000 | 8 yrs | Rating 4 | Active
E024 | Harper Green | Sales | $101000 | 7 yrs | Rating 5 | Active
E025 | Henry Adams | Marketing | $89000 | 6 yrs | Rating 3 | Inactive

=====================================
Employee Analytics
=====================================
1 Display Employees
2 Employees By Department
3 Salary Report
4 Top Performers
5 Highest Salary
6 Department Statistics
7 Active Employees
8 Dashboard
9 Exit
Choice : 2
----------------------------------
Sales
  David Lee
  James Taylor
  Ava Harris
  Liam Allen
  Harper Green
Finance
  Alice Johnson
  Laura Martinez
  William White
  Isabella Hall
  Lucas Scott
HR
  Emily Davis
  Daniel Thomas
  Mia Lewis
  Amelia Wright
IT
  John Smith
  Sarah Brown
  Robert Wilson
  Sophia Jackson
  Noah Walker
  Benjamin King
Marketing
  Michael Chen
  Olivia Anderson
  Ethan Clark
  Charlotte Young
  Henry Adams

=====================================
Employee Analytics
=====================================
1 Display Employees
2 Employees By Department
3 Salary Report
4 Top Performers
5 Highest Salary
6 Department Statistics
7 Active Employees
8 Dashboard
9 Exit
Choice : 3
----------------------------------
Highest Salary : 165000.0
Lowest Salary : 48000.0
Total Salary : 2517000
Average Salary : 100680

Highest Salary : 165000.0
Lowest Salary : 48000.0
Average Salary : 100680.0
Total Salary : 2517000.0
Employee Count : 25

Salary > 100000 (True):
  John Smith
  Alice Johnson
  David Lee
  Sarah Brown
  Michael Chen
  Robert Wilson
  Sophia Jackson
  William White
  Benjamin King
  Lucas Scott
  Harper Green
Salary <= 100000 (False):
  Emily Davis
  Laura Martinez
  James Taylor
  Olivia Anderson
  Daniel Thomas
  Ava Harris
  Ethan Clark
  Mia Lewis
  Noah Walker
  Isabella Hall
  Liam Allen
  Charlotte Young
  Amelia Wright
  Henry Adams

=====================================
Employee Analytics
=====================================
1 Display Employees
2 Employees By Department
3 Salary Report
4 Top Performers
5 Highest Salary
6 Department Statistics
7 Active Employees
8 Dashboard
9 Exit
Choice : 8
----------------------------------
=============================
Employee Analytics Dashboard
=============================
Employees : 25
Average Salary : 100680
Highest Salary : 165000
Lowest Salary : 48000
Departments : 5
Top Performer : John Smith (Rating 5)
Highest Paid Department : IT
Top 5 Highest Salaries
1 John Smith - 165000
2 Alice Johnson - 152000
3 David Lee - 149000
4 Sarah Brown - 141000
5 Michael Chen - 138000
Active Employees : 23
Inactive Employees : 2

=====================================
Employee Analytics
=====================================
1 Display Employees
2 Employees By Department
3 Salary Report
4 Top Performers
5 Highest Salary
6 Department Statistics
7 Active Employees
8 Dashboard
9 Exit
Choice : 9
----------------------------------
Thank You
```



| Operation / API | Used? | Where (method / menu)                | Notes                                                                                                                              |
| --------------- |:-----:|--------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| Lambda `forEach` |  yes  | demonstrateLambdas()                 | printing names, salaries, depts                                                                                                    |
| `Predicate` |  yes  | demonstrateFunctionalInterfaces()    | kind of like putting an if in a variable. Used to check if someone is a high earner.                                               |
| `Function` |  yes  | demonstrateFunctionalInterfaces()    | takes in an employee and transforms it into a String                                                                               |
| `Consumer` |  yes  | demonstrateFunctionalInterfaces()    | just used for printing                                                                                                             |
| `Supplier` |  yes  | demonstrateFunctionalInterfaces()    | only evaluates when topSample.get()                                                                                                |
| `filter` |  yes  | displayFilteredItTopPerformers()     | way more than just one method. Used filter in a lot of these methods. Filters data, very bread and butter operation.               |
| `map` |  yes  | demonstrateMapping()                 | Similar to filter, used in many functions. Maps something to something                                                             |
| `sorted` |  yes  | demonstrateSorting()                 | Used in many functions. Sorts values, doubles in demonstrateSorting().                                                             |
| `distinct` |  yes  | displayDistinctDepartments()         | gets rid of duplicates                                                                                                             |
| `limit` / `skip` |  yes  | displayTopAndNextSalaries()          | skip(n) skips the first n data points and limit(n) takes only the first n data points                                              |
| `count` |  yes  | displayCounts()                      | how many of something there are                                                                                                    |
| `reduce` |  yes  | displayReductions()                  | applies the combiner over and over until data is reduced to one number                                                             |
| `collect(toList/toSet)` |  yes  | demonstrateCollectors()              | collects from the stream and makes a list/set                                                                                      |
| `groupingBy` |  yes  | displayGroupedEmployees()            | groups data by the classifier to split employees by department.                                                                    |
| `partitioningBy` |  yes  | displayPartitionedEmployees()        | like groupingBy but there are only two options                                                                                     |
| `summarizingDouble` |  yes  | displaySummaryStatistics()           | calculates count, sum, max, min, avg                                                                                               |
| `Optional` (`max` / `ifPresent`) |  yes  | displayHighestPaidEmployeeOptional() | a value that may or may not exist. if stream was empty, max would not exist. if stream isn't empty, max exists. runs in both cases |
| Method references |  yes  | basically everywhere                 | lets you do things like employee.getSalary()                                                                                       |
| Dashboard composed report |  yes  | menu 8                               | brings together everything and prints it                                                                                           |


Intermediate operations are lazy. They don't do anything until they are triggered
by a Terminal operation.