package com.academy.bank;

public class Transaction(){

    private String transactionId;
    private double amount;
    private String type;
    private String date;
    private String accountNumber;

    public Transaction(String transactionId, double amount, String type, String date, String accountNumber){
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.accountNumber = accountNumber;
    }

    //------------GETTERS----------------

    public String getTransactionId(){
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void display(){
        System.out.println("Transaction ID: " + transactionId);
        System.out.printf("Amount: %.2f", amount);
        System.out.printf("Type: " + type);
        System.out.println("Date: " + date);
        System.out.println("Account Number: " + accountNumber);

    }
}