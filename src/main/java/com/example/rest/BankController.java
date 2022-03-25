package com.example.rest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {
    HashMap<String, Bankaccount> database;

    public BankController() {
        this.database = new HashMap<>();
    }

    @PostMapping(value = "/addAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAcount(@RequestParam("name") String name){
        Bankaccount account = new Bankaccount(name);
        database.put(account.getBankaccountId(),account);
        System.out.println("Account created");
        return account.getBankaccountId();
    }

    @GetMapping(value = "/getBalance")
    @ResponseStatus(HttpStatus.OK)
    public float getBalance(@RequestParam String id, @RequestParam String name){
        System.out.println(id);
        Bankaccount account = database.get(id);
        //System.out.println(account.getName());
        ArrayList<String> namelist = account.getName();
        for(String namel:namelist) {
            if (Objects.equals(name, namel)) {
                System.out.println("Balance is : " + account.getBalance());
                return account.getBalance();
            }
        }
        System.out.println("no account");
        return -1;
    }



   // @GetMapping(value = "/{id}")
   // @ResponseStatus(HttpStatus.OK)
   // public void getBalance(@PathVariable("id") long bankaccountId){//, @RequestParam String personId){
   //     System.out.println(bankaccountId);
        //Bankaccount account = database.get(bankaccountId);
        // if(personId == account.getPersonId()){
        //     return account.getBalance();
        // }
        // else{
        //    return -1;
        // }
    //}

    @PutMapping(value = "/addMoney")
    @ResponseStatus(HttpStatus.OK)
    public void addMoney(@RequestParam("accountid") String accountid, @RequestParam("amount") String amount){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(accountid + " " + amount);
            Bankaccount account = database.get(accountid);
            account.addMoney(Float.parseFloat(amount));
        }finally{
            lock.unlock();
        }

    }

    @PutMapping(value = "/getMoney")
    @ResponseStatus(HttpStatus.OK)
    public float getMoney(@RequestParam String accountid, @RequestParam String name, @RequestParam String amount){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            Bankaccount account = database.get(accountid);
            ArrayList<String> namelist = account.getName();
            if(Objects.equals(name, namelist.get(0)) | Objects.equals(name, namelist.get(1))){
                float balance = account.getMoney(Float.parseFloat(amount));
                if (balance>=0){
                    System.out.println("afgehaald, total balance now :"+ balance);
                    return balance;
                }
                else{
                    System.out.println("error!!! te weinig geld!");
                    return -1;
                }
            }
            else{
                System.out.println("error not right account");
                return -1;
            }
        }finally{
            lock.unlock();
        }
    }

    @PutMapping(value = "/join")
    @ResponseStatus(HttpStatus.OK)
    public String Join(@RequestParam String accountId1, @RequestParam String name1, @RequestParam String accountId2, @RequestParam String name2){
        Bankaccount account1 = database.get(accountId1);
        Bankaccount account2 = database.get(accountId2);
        if (name1.equals(account1.getName().get(0)) && name2.equals(account2.getName().get(0)) && account1.singlePerson() && account2.singlePerson()) {
            String newaccountId = account1.addPerson(account2.getBalance(), name2);
            database.remove(accountId1);

            database.put(account1.getBankaccountId(),account1);
            database.remove(accountId2);
            return newaccountId;
        }
        return "error";
    }

    @PutMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    public void test(){
        String accountIdJ = addAcount("Jos");
        addMoney(accountIdJ,"594");
        String accountIdA = addAcount("Anne");
        addMoney(accountIdA,"254");
    }


}
