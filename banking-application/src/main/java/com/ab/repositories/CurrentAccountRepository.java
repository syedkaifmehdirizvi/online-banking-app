package com.ab.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ab.entities.CurrentAccount;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Integer>
{
	Optional<CurrentAccount> findByAccountNumber(String accountNumber);
}
