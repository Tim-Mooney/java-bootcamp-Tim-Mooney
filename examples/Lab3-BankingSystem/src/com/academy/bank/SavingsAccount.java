package com.academy.bank;

public class SavingsAccount extends Account implements Printable{

    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, Customer customer, double interestRate){
        super(accountNumber);
        super(balance);
        super(customer);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest(){
        return getBalance() * (interestRate / 100);
    }

    @Override
    public String getAccountType(){
        return "Savings";
    }

    @Override
    public void displayAccount(){
        System.out.println("Savings Account");
        System.out.println("Account number: " + getAccountNumber());
        System.out.println("Customer: " + getCustomer().getName());
        System.out.println("Balance: " + balance);
        System.out.println("Interest rate: " + interestRate);
        System.out.println("Interest: " + calculateInterest());
    }

    public void printDetails(){
        displayAccount();
    }
}