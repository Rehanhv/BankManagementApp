package com.Project.BankManagement.service;

import com.Project.BankManagement.entity.Account;
import com.Project.BankManagement.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepo repo;

    @Override
    public Account createAccount(Account account) {
        Account account_saved = repo.save(account);
        return account_saved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(long accountNumber) {
        Optional<Account> account = repo.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account does not exist");
        }
        Account account_found = account.get();
        return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccounts = repo.findAll();
        return listOfAccounts;
    }

    @Override
    public Account depositAmount(long accountNumber, double amount) {
        Optional<Account> account = repo.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account does not exist");
        }
        Account accountPresent = account.get();
        double totalBalance = accountPresent.getAccount_balance()+amount;
        accountPresent.setAccount_balance(totalBalance);
        repo.save(accountPresent);
        return accountPresent;
    }

    @Override
    public Account withdrawAmount(long accountNumber, double amount) {
        Optional<Account> account = repo.findById(accountNumber);
        Account accountPresent;
        if (account.isEmpty()) {
            throw new RuntimeException("Account does not exist");
        } else {
            accountPresent = account.get();
            if (accountPresent.getAccount_balance() < amount) {
                throw new RuntimeException("Insufficient Balance...");
            }
            else {
                double accountBalance = accountPresent.getAccount_balance() - amount;
                accountPresent.setAccount_balance(accountBalance);
                repo.save(accountPresent);
            }
        }
        return accountPresent;
    }

    @Override
    public Account closeAccount(long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber);
        repo.deleteById(accountNumber);
        return null;
    }
}
