**Checked exceptions**

InvalidAmountException — amount ≤ 0 on deposit/withdraw

InsufficientFundsException — withdraw/transfer amount > balance

InvalidPinException — wrong PIN, or not logged in

AccountNotFoundException — unknown account number

**Unchecked exceptions**

NullPointerException

ArithmeticException

ArrayIndexOutOfBoundsException

**Boundary-level exceptions**

InputMismatchException — non-numeric amount typed in

IOException — transactions.txt unreadable