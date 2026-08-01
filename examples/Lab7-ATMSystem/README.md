# Lab7-ATMSystem

A Java-based ATM (Automated Teller Machine) simulation system that demonstrates core object-oriented programming concepts including exception handling, file I/O, and user interaction patterns.

## Features

- **Account Management** — Create and manage bank accounts with PIN protection
- **Transactions** — Deposit, withdraw, and check balance operations
- **Logging** — Comprehensive error and transaction logging to file
- **Transaction History** — All transactions persisted to `transactions.txt`
- **Exception Handling** — Custom exceptions for invalid operations (insufficient funds, invalid PIN, etc.)

## Prerequisites

- **JDK 21** or higher
- No external dependencies (pure Java)

## Installation & Setup

1. Navigate to the Lab7-ATMSystem directory:
   ```bash
   cd examples/Lab7-ATMSystem
   ```

2. No build step required — plain Java source code

## How to Run

### Option 1: Direct Java Compilation & Execution

1. Compile all Java sources:
   ```bash
   javac -d out src/com/academy/atm/*.java
   ```

2. Run the application:
   ```bash
   java -cp out com.academy.atm.Main
   ```

### Option 2: Using IDE

- Open the project in IntelliJ IDEA, Eclipse, or VS Code
- Set the source root to `src/`
- Run `com.academy.atm.Main`

## Usage

When you start the application, you'll see an interactive menu:

```
1. Create Account
2. Login
3. Exit
```

**Create an account** with a PIN, then **login** to access:
- View balance
- Deposit funds
- Withdraw funds
- View transaction history
- Logout

All transactions are logged to `logs/` and stored in `transactions.txt`.

## Project Structure

```
Lab7-ATMSystem/
├── src/com/academy/atm/
│   ├── Main.java                      # Entry point
│   ├── ATMService.java                # Core ATM logic
│   ├── Account.java                   # Account model
│   ├── Transaction.java               # Transaction record
│   ├── LoggerUtil.java                # Logging utility
│   ├── InvalidPinException.java       # Custom exceptions
│   ├── InsufficientFundsException.java
│   ├── AccountNotFoundException.java
│   └── InvalidAmountException.java
├── logs/                              # Generated log files
├── transactions.txt                   # Generated transaction history
└── README.md                          # This file
```

## Notes

- Accounts and transactions are stored in memory during runtime
- All activity is logged for debugging and audit purposes
- The system uses standard input/output for interactive CLI interaction
- prompt for readme: write a basic readme for Lab7-ATMSystem. include installation steps / how to run it. include a short description. JDK 21
