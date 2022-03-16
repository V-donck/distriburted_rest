package com.example.rest;

import java.util.UUID;

public class Bankaccount {
    private float balance;
    //private UUID personId;
    private UUID bankaccountId;


    public Bankaccount(UUID personId) {
        this.balance = 0;
        this.bankaccountId = UUID.randomUUID();
       //this.personId = personId;
    }

    public UUID getBankaccountId() {
        return bankaccountId;
    }

  // public UUID getPersonId() {
        //return personId;
   // }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void addMoney(float money){
        this.balance = balance + money;
    }

    public void getMoney(float money){
        if(balance - money>= 0) {
            this.balance = balance - money;
        }
        else{
            System.out.println("error!!! te weinig geld!");
        }
    }
}
