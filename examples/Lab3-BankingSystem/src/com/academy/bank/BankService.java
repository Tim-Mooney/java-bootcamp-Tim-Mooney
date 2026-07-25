package com.academy.bank;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BankService {

    private static final int MAX_CUSTOMERS = 50;
    private static final int MAX_ACCOUNTS = 100;
    private static final int MAX_TRANSACTIONS = 500;

    private final Customer[] customers = new Customer[MAX_CUSTOMERS];
    private final Account[] accounts = new Account[MAX_ACCOUNTS];
    private final Transaction[] transactions = new Transaction[MAX_TRANSACTIONS];

    private int customerCount = 0;
    private int accountCount = 0;
    private int transactionCount = 0;
    private int nextAccountNumber = 10001;
    private int nextTransactionNumber = 1;

    private final Scanner scanner;

    public BankService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createCustomer() {
        String choiceInput;
        String customerID;
        String name;
        String email;
        String phone;

        System.out.print("Enter customer ID: ");
        choiceInput = scanner.nextLine().trim();
        if (choiceInput.isEmpty()) {
            System.out.println("Empty customer ID.");    //get ID
            return;
        }
        for(int i = 0; i < customerCount; i++){
            if(customers[i].getCustomerId().equals(choiceInput)){
                System.out.println("Customer ID already in use.");
            }
        }
        customerID = choiceInput;

        System.out.println("Enter name: ");
        if (choiceInput.isEmpty()) {
            System.out.println("Must enter a name.");    //get name
            return;
        }
        name = choiceInput;

        System.out.println("Enter an email: ");
        if (choiceInput.isEmpty()) {
            System.out.println("Must enter an email.");    //get email
            return;
        }
        email = choiceInput;

        System.out.println("Enter a phone number: ");
        if (choiceInput.isEmpty()) {
            System.out.println("Must enter a phone number.");    //get phone
            return;
        }

        phone = choiceInput;

        customers[customerCount] = new Customer(customerID, name, email, phone);
        customerCount++;

        System.out.println("Customer created successfully.");
    }

    public void createSavingsAccount() {

        String choiceInput;
        Customer customer;
        Double initBal;
        Double interest;

        System.out.print("Enter customer ID: ");
        choiceInput = scanner.nextLine().trim();
        if (choiceInput.isEmpty()) {
            System.out.println("Empty customer ID.");    //get ID
            return;
        }

        for(int i = customerCount-1; i > 0; i--) {
            if (customers[i].getCustomerId().equals(choiceInput)) {
                customer = customers[i];
                break;
            }
            return;
        }


        System.out.print("Enter initial balance: ");
        choiceInput = scanner.nextLine().trim();
        try {
            initBal = Double.parseDouble(choiceInput);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            System.out.println("Please Try Again.");
            return;
        }

        System.out.print("Enter interest rate: ");
        choiceInput = scanner.nextLine().trim();
        try {
            interest = Double.parseDouble(choiceInput);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            System.out.println("Please Try Again.");
            return;
        }

        SavingsAccount account = new SavingsAccount(Integer.toString(nextAccountNumber), initBal, customer, interest);
        nextAccountNumber++;
        accounts[accountCount] = account;
        accountCount++;

    }

    public void createCurrentAccount() {

        String choiceInput;
        Customer customer;
        Double initBal;
        Double fee;

        System.out.print("Enter customer ID: ");
        choiceInput = scanner.nextLine().trim();
        if (choiceInput.isEmpty()) {
            System.out.println("Empty customer ID.");    //get ID
            return;
        }

        for(int i = customerCount-1; i > 0; i--){
            if(customers[i].getCustomerId().equals(choiceInput)){
                customer = customers[i];
                break;
            }
        }


        System.out.print("Enter initial balance: ");
        choiceInput = scanner.nextLine().trim();
        try {
            initBal = Double.parseDouble(choiceInput);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            System.out.println("Please Try Again.");
            return;
        }

        System.out.print("Enter transaction fee: ");
        choiceInput = scanner.nextLine().trim();
        try {
            fee = Double.parseDouble(choiceInput);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            System.out.println("Please Try Again.");
            return;
        }

        CurrentAccount account = new CurrentAccount(Integer.toString(nextAccountNumber), initBal, customer, fee);
        nextAccountNumber++;
        accounts[accountCount] = account;
        accountCount++;


    }

    public void deposit() {
        String choiceInput;
        Account account;
        String accountNumber;
        Double amount;


        System.out.print("Enter account number: ");
        choiceInput = scanner.nextLine().trim();
        if (choiceInput.isEmpty()) {
            System.out.println("Empty account number.");    //get account number
            return;
        }

        for(int i = accountCount-1; i > 0; i--){
            if(accounts[i].getAccountNumber().equals(choiceInput)){
                account = accounts[i];
                break;
            }
        }
        accountNumber = choiceInput;

        System.out.println("Enter the amount you want to deposit: ");
        choiceInput = scanner.nextLine().trim();
        try {
            amount = Double.parseDouble(choiceInput);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            System.out.println("Please Try Again.");
            return;
        }
        if(amount <= 0){
            System.out.println("Amount must be greater than 0");
            return;
        }

        account.deposit(amount);



        Transaction transaction = new Transaction(Integer.toString(nextTransactionNumber), amount, "DEPOSIT", LocalDate.now().toString(), accountNumber);
        nextTransactionNumber++;
        System.out.println("Balance updated: "+ account.getBalance());
    }

    public void withdraw() {
        String choiceInput;
        Account account;
        String accountNumber;
        Double amount;


        System.out.print("Enter account number: ");
        choiceInput = scanner.nextLine().trim();
        if (choiceInput.isEmpty()) {
            System.out.println("Empty account number.");    //get account number
            return;
        }

        for(int i = accountCount-1; i > 0; i--){
            if(accounts[i].getAccountNumber().equals(choiceInput)){
                account = accounts[i];
                break;
            }
        }
        accountNumber = choiceInput;

        System.out.println("Enter the amount you want to withdraw: ");
        choiceInput = scanner.nextLine().trim();
        try {
            amount = Double.parseDouble(choiceInput);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid Input");
            System.out.println("Please Try Again.");
            return;
        }
        if(amount <= 0){
            System.out.println("Amount must be greater than 0.");
            return;
        }
        if(amount > account.getBalance()){
            System.out.println("Amount cannot be greater than balance.");
            return;
        }

        account.withdraw(amount);



        Transaction transaction = new Transaction(Integer.toString(nextTransactionNumber), amount, "WITHDRAW", LocalDate.now().toString(), accountNumber);
        nextTransactionNumber++;

        if(account.getAccountType().equals("Current")){
            System.out.printf("Fee is $%.2f"+ account.calculateCharges() +". Total deducted: $%.2f" + account.calculateCharges() + amount + "\n");
        }

        System.out.println("Balance updated: "+ account.getBalance());


    }

    public void displayAccounts() {
        if(accountCount == 0){
            System.out.println("No accounts to display");
        }
        System.out.println("----------------------------------");
        for (int i = 0; i < accountCount; i++) {
            accounts[i].displayAccount();
            System.out.println("----------------------------------");
        }

    }

    public void displayCustomers() {
        if (customerCount == 0) {
            System.out.println("No customers available.");
            return;
        }

        System.out.println("----------------------------------");
        for (int i = 0; i < customerCount; i++) {
            customers[i].display();
            System.out.println("----------------------------------");
        }
    }

    public void transferMoney() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayTransactionHistory() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayAccountsSortedByBalance() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void displayHighestBalanceCustomer() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    public void generateAccountSummaryReport() {
        System.out.println("Bonus / full-path feature — implement after core TODOs.");
    }

    private Customer readExistingCustomer() {
        if (customerCount == 0) {
            System.out.println("Create a customer first.");
            return null;
        }

        System.out.print("Customer ID : ");
        String customerId = scanner.nextLine().trim();
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
        }

        return customer;
    }

    private Account readExistingAccount() {
        if (accountCount == 0) {
            System.out.println("No accounts available.");
            return null;
        }

        System.out.print("Account Number : ");
        String accountNumber = scanner.nextLine().trim();
        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
        }

        return account;
    }

    private Customer findCustomer(String customerId) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equalsIgnoreCase(customerId)) {
                return customers[i];
            }
        }
        return null;
    }

    private Account findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }

    private void recordTransaction(String accountNumber, double amount, String type) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            return;
        }

        String transactionId = "T" + nextTransactionNumber++;
        String date = LocalDate.now().toString();
        transactions[transactionCount++] = new Transaction(transactionId, amount, type, date, accountNumber);
    }

    private double readPositiveAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < 0) {
                    System.out.println("Amount must not be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid amount. Please try again.");
            }
        }
    }
}