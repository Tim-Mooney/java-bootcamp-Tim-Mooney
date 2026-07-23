package com.academy.bank;

public class CurrentAccount extends Account implements Printable{

    private double transactionFee;

    public SavingsAccount(String accountNumber, double balance, Customer customer, double transactionFee){
        super(accountNumber);
        super(balance);
        super(customer);
        this.transactionFee = transactionFee;
    }

    @Override
    public double calculateCharges(){
        return transactionFee;
    }

    @Override
    public void displayAccount(){
        System.out.println("Current Account");
        System.out.println("Account number: " + getAccountNumber());
        System.out.println("Customer: " + getCustomer().getName());
        System.out.println("Balance: " + balance);
        System.out.println("Transaction fee: " + transactionFee);
    }

    @Override
    public String getAccountType(){
        return "Savings";
    }

    public void printDetails(){
        displayAccount();
    }


}