package com.ab.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.entities.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer>
{
	Optional<SavingsAccount> findByAccountNumber(String accountNumber);
}
