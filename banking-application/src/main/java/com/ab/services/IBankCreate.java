package com.ab.services;

import com.ab.entities.CurrentAccount;
import com.ab.entities.SavingsAccount;

public interface IBankCreate 
{
	public SavingsAccount createSavingsAccount(String username, String firstName, String lastName, double openingBalance);
    public CurrentAccount createCurrentAccount(String username, String firstName, String lastName, double openingBalance, double overdraftLimit);
}
