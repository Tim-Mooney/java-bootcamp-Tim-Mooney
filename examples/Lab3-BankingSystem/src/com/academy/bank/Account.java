package com.academy.bank;

public abstract class Account{

    private String accountNumber;
    private double balance;
    private Customer customer;

    protected Account(String accountNumber, double balance, Customer customer){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
    }

    //------------GETTERS----------------

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    //------------SETTERS----------------


//    public void setAccountNumber(String accountNumber) {
//        this.accountNumber = accountNumber;
//    }
//
//    protected void setBalance(double bal){      //i think i misinterpreted the instructions
//        this.balance = bal;                      //uncomment if these are required
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    //---------------------------------

    public void deposit(double amount){
        if(amount <= 0){
            return;
        }
        balance += amount;
    }

    public boolean withdraw(double amount){
        double totalWithdraw = amount + calculateCharges();
        if(balance >= totalWithdraw){
            balance -= totalWithdraw;
            return true;
        }
        return false;
    }

    public double calculateCharges(){
        return 0.0;
    }

    public double calculateInterest(){
        return 0.0;
    }

    public String getAccountType(){
        return "Account";
    }

    public abstract void displayAccount();
}