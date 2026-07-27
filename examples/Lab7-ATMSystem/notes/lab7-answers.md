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