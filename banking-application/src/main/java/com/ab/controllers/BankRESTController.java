package com.ab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ab.entities.CurrentAccount;
import com.ab.entities.SavingsAccount;
import com.ab.entities.Transaction;
import com.ab.services.BankServiceImpl;

@RestController
@RequestMapping("/accounts")
public class BankRESTController 
{
	@Autowired
    private BankServiceImpl bankService;

    @PostMapping("/savings")
    public SavingsAccount createSavingsAccount(@RequestBody SavingsAccount savingsAccount) {
        return bankService.createSavingsAccount(savingsAccount.getUser().getUsername(), savingsAccount.getFirstName(), savingsAccount.getLastName(), savingsAccount.getBalance());
    }

    @PostMapping("/current")
    public CurrentAccount createCurrentAccount(@RequestBody CurrentAccount currentAccount) {
        return bankService.createCurrentAccount(currentAccount.getUser().getUsername(), currentAccount.getFirstName(), currentAccount.getLastName(), currentAccount.getBalance(), currentAccount.getOverdraftLimit());
    }

    @PutMapping("/{accountNumber}/deposit")
    public String deposit(@PathVariable("accountNumber") String accountNumber, @RequestBody Double amount) {
        bankService.deposit(accountNumber, amount);
        return "Deposit successful";
    }

    @PutMapping("{accountNumber}/withdraw")
    public String withdraw(@PathVariable("accountNumber") String accountNumber, @RequestBody Double amount) {
        bankService.withdraw(accountNumber, amount);
        return "Withdrawal successful";
    }

    @GetMapping("{accountNumber}/transactions")
    public List<Transaction> getTransactions(@PathVariable("accountNumber") String accountNumber) {
        return bankService.getTransactions(accountNumber);
    }
   
    
}
