package com.ab.services;

import java.util.List;

import com.ab.entities.Transaction;

public interface IBankTransaction 
{
	public void deposit(String accountNumber, double amount);
    public void withdraw(String accountNumber, double amount); 
    public List<Transaction> getTransactions(String accountNumber);
}
