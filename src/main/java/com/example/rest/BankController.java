package com.example.rest;


import java.util.HashMap;
import java.util.Objects;

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
    public void addAcount(@RequestParam("name") String name){
        Bankaccount account = new Bankaccount(name);
        database.put(account.getBankaccountId(),account);
    }

    @GetMapping(value = "/getBalance")
    @ResponseStatus(HttpStatus.OK)
    public float getBalance(@RequestParam String id, @RequestParam String name){
        System.out.println(id);
        Bankaccount account = database.get(id);
        System.out.println(account.getName());
        if(Objects.equals(name, account.getName())){
            System.out.println("Balance is : " + account.getBalance());
            return account.getBalance();
        }
        else{
            System.out.println("no account");
            return -1;
        }
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

    @PutMapping(value = "/addMoney/")
    @ResponseStatus(HttpStatus.OK)
    public void addMoney(@RequestParam("accountid") String accountid, @RequestParam("amount") String amount){
        System.out.println(accountid + " " + amount);
        Bankaccount account = database.get(accountid);
        account.addMoney(Float.parseFloat(amount));

    }

    @PutMapping(value = "/getMoney/")
    @ResponseStatus(HttpStatus.OK)
    public float getMoney(@RequestParam String accountid, String name, String amount){
        Bankaccount account = database.get(accountid);
        if(Objects.equals(name, account.getName())){
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
    }



}
