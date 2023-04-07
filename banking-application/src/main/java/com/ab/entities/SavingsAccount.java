package com.ab.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "savings_accounts")
public class SavingsAccount extends BankAccount
{
	
	public static final String ACCOUNT_TYPE_PREFIX = "SA";

	private Double interestRate;
	
	
	// Constructor
	
	public SavingsAccount() 
	{
		super();
	}

//	public SavingsAccount(String accountNumber, String firstName, String lastName, double balance, LocalDateTime dateOpened, Double interestRate, User user) 
//	{
//		super(accountNumber, firstName, lastName, balance, dateOpened, user);
//		this.interestRate = interestRate;
//	}
	
	public SavingsAccount(String firstName, String lastName, double balance, LocalDateTime dateOpened, Double interestRate, User user) {
        super(ACCOUNT_TYPE_PREFIX + generateRandomNumber(), firstName, lastName, balance, dateOpened, user);
        this.interestRate = interestRate;
    }
	
	
	// Getters and Setters
	
	public Double getInterestRate() 
	{
		return interestRate;
	}

	public void setInterestRate(Double interestRate) 
	{
		this.interestRate = interestRate;
	}

	
	
}
