package com.Project.BankManagement.controller;


import com.Project.BankManagement.entity.Account;
import com.Project.BankManagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {


    @Autowired
    AccountService service;


    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account createAccount = service.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }

    @GetMapping("/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable long accountNumber){
        Account account = service.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }

    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountDetails(){
        List<Account> allAccountDetails = service.getAllAccountDetails();
        return allAccountDetails;
    }

    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAmount(@PathVariable long accountNumber, @PathVariable double amount){
        Account account = service.depositAmount(accountNumber, amount);
        return account;
    }

    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdrawAmount(@PathVariable long accountNumber, @PathVariable double amount){
        Account account = service.withdrawAmount(accountNumber, amount);
        return account;
    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable long accountNumber){
        service.closeAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account Closed...");
    }
}
