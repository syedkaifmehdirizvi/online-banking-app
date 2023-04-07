package com.ab.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.entities.BankAccount;
import com.ab.entities.CurrentAccount;
import com.ab.entities.SavingsAccount;
import com.ab.entities.Transaction;
import com.ab.entities.User;
import com.ab.repositories.BankAccountRepository;
import com.ab.repositories.CurrentAccountRepository;
import com.ab.repositories.SavingsAccountRepository;
import com.ab.repositories.TransactionRepository;
import com.ab.repositories.UserRepository;

@Service
public class BankServiceImpl implements IBankCreate, IBankTransaction
{
	@Autowired
    private BankAccountRepository bankAccountRepository;
	@Autowired
	private CurrentAccountRepository currentAccountRepository;
	@Autowired
	private SavingsAccountRepository savingsAccountRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	
	// Business Logic
	
    @Override
    public SavingsAccount createSavingsAccount(String username, String firstName, String lastName, double openingBalance) 
    {
    	User user = userRepository.findByUsername(username).orElseGet(() -> 																						// Reference: https: www.baeldung.com/java-optional-or-else-vs-or-else-get
    	{
            User newUser = new User(username, "password", firstName, lastName);
            return userRepository.save(newUser);
        });
    	
        SavingsAccount savingsAccount = new SavingsAccount(firstName, lastName, openingBalance, LocalDateTime.now(), 0.0, user);
        return savingsAccountRepository.save(savingsAccount);
    }

    @Override
    public CurrentAccount createCurrentAccount(String username, String firstName, String lastName, double openingBalance, double overdraftLimit) 
    {
        User user = userRepository.findByUsername(username).orElseGet(() -> {																						// Reference: https: www.baeldung.com/java-optional-or-else-vs-or-else-get
            User newUser = new User(username, "password", firstName, lastName);
            return userRepository.save(newUser);
        });
    	
        CurrentAccount currentAccount = new CurrentAccount(firstName, lastName, openingBalance, LocalDateTime.now(), overdraftLimit, user);
        return currentAccountRepository.save(currentAccount);
    }

    @Override
    public void deposit(String accountNumber, double amount) 
    {
    	transactionRepository.deposit(accountNumber, amount);

        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account not found"));			// Reference: https: www.baeldung.com/java-optional-or-else-vs-or-else-get

        Transaction transaction = new Transaction(bankAccount, "Deposit", amount, LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    public void withdraw(String accountNumber, double amount) 
    {
    	transactionRepository.withdraw(accountNumber, amount);

        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException("Account not found"));			// Reference: https: www.baeldung.com/java-optional-or-else-vs-or-else-get

        Transaction transaction = new Transaction(bankAccount, "Withdrawal", amount, LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(String accountNumber) 
    {
        return bankAccountRepository.getTransactionsByAccountNumber(accountNumber);
    }
    
	
}
