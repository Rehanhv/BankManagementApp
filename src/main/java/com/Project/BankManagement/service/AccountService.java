package com.Project.BankManagement.service;

import com.Project.BankManagement.entity.Account;

import java.util.List;

public interface AccountService {

    public Account createAccount(Account account);
    public Account getAccountDetailsByAccountNumber(long accountNumber);
    public List<Account> getAllAccountDetails();
    public Account depositAmount(long accountNumber, double amount);
    public Account withdrawAmount(long accountNumber, double amount);
    public Account closeAccount(long accountNumber);
}
