package com.example.rest;


import java.util.ArrayList;

public class Bankaccount {
    private float balance;
    private String accountId;
    private final ArrayList<String> name  = new ArrayList<>();


    public Bankaccount(String name) {
        this.balance = 0;
        this.accountId = name + "acc";
        this.name.add(name);
    }

    public String getBankaccountId() {
        return accountId;
    }

    public ArrayList<String> getName() {
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

    public void addPerson(float money, String name){
        this.balance= balance + money;
        this.name.add(name);
        this.accountId = this.name.get(0) + "_" + this.name.get(1) + "acc";
    }

    public boolean singlePerson (){
        return name.size() == 1;
    }
}
