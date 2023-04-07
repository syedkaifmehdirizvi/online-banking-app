package com.ab.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.entities.BankAccount;
import com.ab.entities.Transaction;

import jakarta.transaction.Transactional;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> 
{
	
    
    @Query("SELECT t FROM Transaction t WHERE t.bankAccount.accountNumber = :accountNumber ORDER BY t.transactionTime DESC")
    public List<Transaction> getTransactionsByAccountNumber(@Param("accountNumber") String accountNumber);
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO transactions (bank_account_id, transaction_type, amount, transaction_time) VALUES (:accountNumber, 'Deposit', :amount, NOW()); "
    		+ "UPDATE bank_account b SET b.balance = b.balance + :amount WHERE b.account_number = :accountNumber", nativeQuery = true)
    public void depositWithTransaction(String accountNumber, double amount);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO transactions (bank_account_id, transaction_type, amount, transaction_time) SELECT :accountNumber, 'Withdrawal', :amount, NOW() FROM bank_account b WHERE b.account_number = :accountNumber AND b.balance >= :amount; "
    		+ "UPDATE bank_account b SET b.balance = b.balance - :amount WHERE b.account_number = :accountNumber AND b.balance >= :amount", nativeQuery = true)
    public void withdrawWithTransaction(String accountNumber, double amount);
    
    @Query("SELECT b FROM BankAccount b WHERE b.accountNumber = ?1")
    Optional<BankAccount> findByAccountNumber(String accountNumber);    
}
	
	
