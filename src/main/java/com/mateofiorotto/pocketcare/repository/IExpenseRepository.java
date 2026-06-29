package com.mateofiorotto.pocketcare.repository;

import com.mateofiorotto.pocketcare.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, UUID> {
}
