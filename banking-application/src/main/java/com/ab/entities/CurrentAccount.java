package com.ab.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "current_accounts")
public class CurrentAccount extends BankAccount
{
	public static final String ACCOUNT_TYPE_PREFIX = "CA";
	
    private Double overdraftLimit;
    
    
    // Constructor
    
	public CurrentAccount() 
	{
		super();
	}

//	public CurrentAccount(String accountNumber, String firstName, String lastName, double balance, LocalDateTime dateOpened, Double overdraftLimit, User user) 
//	{
//		super(accountNumber, firstName, lastName, balance, dateOpened, user);
//		this.overdraftLimit = overdraftLimit;
//	}
	
	public CurrentAccount(String firstName, String lastName, double balance, LocalDateTime dateOpened, Double overdraftLimit, User user) {
        super(ACCOUNT_TYPE_PREFIX + generateRandomNumber(), firstName, lastName, balance, dateOpened, user);
        this.overdraftLimit = overdraftLimit;
    }
	
	// Getters and Setters

	public Double getOverdraftLimit() 
	{
		return overdraftLimit;
	}

	public void setOverdraftLimit(Double overdraftLimit) 
	{
		this.overdraftLimit = overdraftLimit;
	}

	
}
