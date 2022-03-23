package com.example.rest;


public class Bankaccount {
    private float balance;
    private final String name;
    private final String accountId;


    public Bankaccount(String name) {
        this.balance = 0;
        this.accountId = name + "acc";
        this.name = name;
    }

    public String getBankaccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void addMoney(float money){
        this.balance = balance + money;
    }

    public float getMoney(float money){
        if(balance - money>= 0) {
            this.balance = balance - money;
            return balance;
        }
        else{
            System.out.println("error!!! te weinig geld!");
            return -1;
        }
    }
}
