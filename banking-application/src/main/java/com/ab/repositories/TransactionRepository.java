package com.ab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ab.entities.Transaction;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
	@Transactional
    @Modifying
    @Query("UPDATE BankAccount b SET b.balance = b.balance + :amount WHERE b.accountNumber = :accountNumber")     
    public void deposit(String accountNumber, double amount);
	
	@Transactional
    @Modifying
    @Query("UPDATE BankAccount b SET b.balance = b.balance - :amount WHERE b.accountNumber = :accountNumber AND b.balance >= :amount")
    public void withdraw(String accountNumber, double amount); 
}
