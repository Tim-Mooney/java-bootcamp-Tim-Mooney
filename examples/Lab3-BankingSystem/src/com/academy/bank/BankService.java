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
        if(customerCount >= MAX_CUSTOMERS){
            System.out.println("Full of customers");
            return;
        }

        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine().trim();
        if (customerId.isEmpty()) {
            System.out.println("Empty customer ID.");    //get ID
            return;
        }
        if(findCustomer(customerId) != null){
            System.out.println("Customer ID taken");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Must enter a name.");    //get name
            return;
        }

        System.out.print("Enter an email: ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) {
            System.out.println("Must enter an email.");    //get email
            return;
        }

        System.out.print("Enter a phone number: ");
        String phone = scanner.nextLine().trim();
        if (phone.isEmpty()) {
            System.out.println("Must enter a phone number.");    //get phone
            return;
        }


        customers[customerCount++] = new Customer(customerId, name, email, phone);

        System.out.println("Customer created successfully.");
    }

    public void createSavingsAccount() {
        if(accountCount >= MAX_ACCOUNTS){
            System.out.println("Accounts full");
            return;
        }

        Customer customer = readExistingCustomer();
        if(customer == null){
            return;
        }

        double initBal = readPositiveAmount("Enter initial balance: ");
        double interest = readPositiveAmount("Enter interest rate: ");

        SavingsAccount account = new SavingsAccount(Integer.toString(nextAccountNumber), initBal, customer, interest);
        nextAccountNumber++;
        accounts[accountCount] = account;
        accountCount++;

        System.out.println("Created savings account");
        account.printDetails();

    }

    public void createCurrentAccount() {

        if(accountCount >= MAX_ACCOUNTS){
            System.out.println("Accounts full");
            return;
        }

        Customer customer = readExistingCustomer();
        if(customer == null){
            return;
        }

        double initBal = readPositiveAmount("Enter initial balance: ");
        double fee = readPositiveAmount("Enter transaction fee: ");

        CurrentAccount account = new CurrentAccount(Integer.toString(nextAccountNumber), initBal, customer, fee);
        nextAccountNumber++;
        accounts[accountCount] = account;
        accountCount++;

        System.out.println("Created current account");
        account.printDetails();

    }

    public void deposit() {
        Account account = readExistingAccount();
        if(account == null){
            return;
        }

        double amount = readPositiveAmount("Enter the amount for the deposit: ");   //this allows you to deposit 0 which I guess doesn't hurt anything


        account.deposit(amount);

        recordTransaction(account.getAccountNumber(), amount, "DEPOSIT");
        System.out.println("Balance updated: "+ account.getBalance());
    }

    public void withdraw() {
        Account account = readExistingAccount();
        if(account == null){
            return;
        }

        double amount = readPositiveAmount("Enter the amount for the withdrawal: ");
        if(amount > account.getBalance()) {
            System.out.println("Cannot withdraw more than balance.");
            return;
        }


        account.withdraw(amount);

        recordTransaction(account.getAccountNumber(), amount, "WITHDRAW");


        if(account.getAccountType().equals("Current")){
            System.out.printf("Fee is $%.2f. Total deducted: $%.2f%n" + account.calculateCharges(), account.calculateCharges() + amount);
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