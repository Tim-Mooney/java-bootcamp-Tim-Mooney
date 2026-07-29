**DEMO RUN**

```azure
=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 1
--------------------------------
Enter Account Number : 1001
Enter PIN : 1234
Login Successful
Transaction Completed.
Returning to Main Menu.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 3
--------------------------------
Amount : 20000
ERROR
Insufficient Balance
Transaction Cancelled
Transaction Completed.
Returning to Main Menu.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 2
--------------------------------
Amount : -100
ERROR
Amount must be greater than zero.
Transaction Completed.
Returning to Main Menu.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 2
--------------------------------
Amount : 1000
Deposit Successful
Current Balance : 12000
Transaction Completed.
Returning to Main Menu.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 2
--------------------------------
Amount : abc

ERROR
Invalid numeric input.
Please enter a valid amount.
Transaction Completed.
Returning to Main Menu.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 6
--------------------------------
Session Transactions:
2026-07-27T14:39:28.160940 | Withdraw | $0.00 | FAILED | Transaction Failed. Insufficient Account Balance.
2026-07-27T14:39:55.049453 | Deposit | $0.00 | FAILED | Amount must be greater than zero.
2026-07-27T14:40:00.849572 | DEPOSIT | $1000.00 | SUCCESS | Deposit successful
2026-07-27T14:40:14.096055 | Deposit | $0.00 | FAILED | Invalid numeric input.

Historical Transactions (from file):
2026-01-10,DEPOSIT,1001,500.00,Initial funding
2026-01-12,WITHDRAW,1001,150.00,ATM withdrawal
2026-01-15,DEPOSIT,1002,1200.00,Salary deposit
2026-01-18,TRANSFER,1001,200.00,Transfer to 1002
Transaction Completed.
Returning to Main Menu.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 1
--------------------------------
Already logged in as John Smith.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 7  
--------------------------------
Thank You
```

**INCORRECT PIN**

```azure

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 1
--------------------------------
Enter Account Number : 1001
Enter PIN : 1235
ERROR
Invalid PIN entered. Attempts remaining: 2
Transaction Completed.
Returning to Main Menu.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 1   
--------------------------------
Enter Account Number : 1001
Enter PIN : 654
ERROR
Invalid PIN entered. Attempts remaining: 1
Transaction Completed.
Returning to Main Menu.

=================================
ATM Banking System
=================================
1 Login
2 Deposit
3 Withdraw
4 Balance Inquiry
5 Transfer
6 Mini Statement
7 Exit
8 Unchecked Exception Demo
9 Daily Error Report (Bonus)
10 Transaction Summary (Bonus)
Choice : 1
--------------------------------
Enter Account Number : 1001
Enter PIN : 7654
ERROR
Invalid PIN entered. Attempts remaining: 0
Maximum PIN attempts reached. Login locked for this session.
Transaction Completed.
Returning to Main Menu.

```

**Why are InvalidAmountException and friends checked in this lab, while NullPointerException is unchecked?**

Invalid amount and the other checked exceptions are expected and recoverable, but things like NullPointerException point to programming errors.
Invalid amount exceptions are expected and intended to happen when users use the system, there should not be any NullPointerExceptions if the code is right.

**What does throws on Account.withdraw(...) force callers to do?**

It forces callers to either catch the exception or propagate the exception up the chain.

**Why catch specific exceptions before a broad catch (Exception ex)?**

Each exception needs to be handled differently. A broad catch would catch everything immediately and then all the specific catches would never run.

**What guarantee does finally give you that catch alone does not?**

Finally runs no matter what. If there is or isn't an exception, code in finally always runs.

**Why prefer try-with-resources over reader.close() in a finally block?**

If reader throws an exception it can cause exception masking. Also, try-with-resources is cleaner to read.

**Why log stack traces to a file while showing short messages to the ATM user?**

The user doesn't care about stack traces, only the programmer does. The user needs to know what went wrong in plain english.

**Where should validation throw—deep in Account or only in Main? Why?**

Should throw in Account. Account is where a transaction is deemed valid or invalid. Also, if anything else calls Account.withdraw() or Account.deposit(), the transactions are still being validated.

**How will CRM later reuse “domain exception + boundary catch + log” (without claiming CRM is done today)?**

The pattern of having a class like Account throwing domain exceptions and having a different function that catches all the exceptions and handles and logs them holds.

**What is the difference between checked and unchecked exceptions?**

Checked exceptions are exceptions that the compiler forces you to handle, either by catching them or declaring them in the method signature. 
Unchecked exceptions, on the other hand, are not checked at compile time and typically represent programming errors (like NullPointerException) that should be fixed in the code rather than handled.

**Why should custom exceptions be used?**

Allows for more specific error handling and better communication of what went wrong. 
Custom exceptions can carry additional information and context about the error, making it easier to debug and maintain the code.

**What is exception propagation?**

When and exception is thrown and not caught in the same method, it propagates up the call stack to the calling method.
If it is not caught there, it continues to propagate up until it is caught or reaches the top level of the application.

**What is the purpose of finally?**

Finally contains code that is guaranteed to run regardless of whether an exception was thrown or caught.

**Why is try-with-resources preferred?**

It automatically closes resources that implement the AutoCloseable interface, reducing the risk of resource leaks and making the code cleaner and easier to read.

**When should throw be used?**

throw is used to explicitly throw an exception

**When should throws be used?**

throws is used in a method signature to declare that the method might throw a specific type of exception.

**Why is logging important in enterprise applications?**

logging is important for diagnosing issues, monitoring application behavior, and providing insights into the application's performance and usage. 
It helps developers understand what happened in the application, especially when errors occur.

**What happens if an exception is not handled?**

If an exception is not handled, it can propagate up the call stack. 
If it reaches the top level without being caught, the program will terminate.

**How does proper exception handling improve software reliability?**

Proper exception handling improves software reliability by ensuring that errors are caught and handled gracefully, preventing the application from crashing and providing useful feedback to users or developers.

**(Forward look) How would a future CRM map domain exceptions (not found / validation) to API errors using the same boundary-catch + log pattern—without claiming CRM is implemented today?**

A future CRM could map domain exceptions to API errors by using a similar pattern of catching exceptions at the boundary of the application (e.g., in a controller or service layer) and then logging the exception details while returning a user-friendly error response to the API consumer.