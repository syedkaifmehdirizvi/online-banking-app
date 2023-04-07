package com.ab.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "accountNumber")
    @JsonIgnore
    private BankAccount bankAccount;
    
    private String accountType;
    
    private String transactionType;
    
    private double amount;
    
    private double balance;
    
    private LocalDateTime transactionTime;

    public Transaction() 
    {
    	
    }

    public Transaction(BankAccount bankAccount, String accountType, String transactionType, double amount, double balance, LocalDateTime transactionTime) 
    {
        this.bankAccount = bankAccount;
        this.accountType = accountType;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.transactionTime = transactionTime;
    }

    public Transaction(BankAccount bankAccount, String transactionType, double amount, LocalDateTime transactionTime) {
        this.bankAccount = bankAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
    
    
}
