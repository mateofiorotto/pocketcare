package com.mateofiorotto.pocketcare.service.expense;

import com.mateofiorotto.pocketcare.dto.expense.ExpenseResponseDTO;

import java.util.List;

public interface IExpenseService {
    List<ExpenseResponseDTO> getExpensesList();

    ExpenseResponseDTO getExpenseById(Long id);

    ExpenseResponseDTO createExpense(ExpenseRequestDTO request);

    ExpenseResponseDTO updateExpense(Long id, ExpenseRequestDTO request);

    void deleteExpense(Long id);
}
