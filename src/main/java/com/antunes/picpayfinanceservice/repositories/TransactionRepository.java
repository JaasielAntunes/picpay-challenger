package com.antunes.picpayfinanceservice.repositories;

import com.antunes.picpayfinanceservice.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
