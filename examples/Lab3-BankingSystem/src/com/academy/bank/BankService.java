package com.academy.bank;

public class BankService() {

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

    public void createCustomer(){

    }

    public void createSavingsAccount(){

    }

    public void createCurrentAccount(){

    }

    public void deposit(){

    }

    public void withdraw(){

    }

    public void displayAccounts(){

    }

    public void displayCustomers(){

    }

    public void cleanUp(){

    }
}