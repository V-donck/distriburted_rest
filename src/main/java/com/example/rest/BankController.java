package com.example.rest;


import java.util.HashMap;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {
    HashMap<UUID, Bankaccount> database;

    public BankController() {
        this.database = new HashMap<UUID, Bankaccount>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAcount(UUID personId){
        Bankaccount account = new Bankaccount(personId);
        database.put(account.getBankaccountId(),account);
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public void getBalance(@RequestParam long id){//, @RequestParam String personId){
        System.out.println(id);
        //Bankaccount account = database.get(bankaccountId);
       // if(personId == account.getPersonId()){
       //     return account.getBalance();
       // }
       // else{
        //    return -1;
       // }
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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void addMoney(UUID bankaccountId, float amount){
        Bankaccount account = database.get(bankaccountId);
        account.addMoney(amount);
    }

    public boolean getMoney(UUID bankaccountId, UUID personId, float amount){
        Bankaccount account = database.get(bankaccountId);
        //if(personId == account.getPersonId()){
            account.getMoney(amount);
            return true;
       // }
        //else{
         //   return false;
       // }
    }

}
