package com.mateofiorotto.pocketcare.service.expense;

import com.mateofiorotto.pocketcare.dto.expense.ExpenseResponseDTO;
import com.mateofiorotto.pocketcare.dto.expense.ExpenseRequestDTO;

import java.util.List;
import java.util.UUID;

public interface IExpenseService {
    /**
     * Get the list of all own expenses
     * @return
     */
    List<ExpenseResponseDTO> getExpensesList();

    /**
     * Get specific expense by ID
     * @param id
     * @return
     */
    ExpenseResponseDTO getExpenseById(UUID id);

    /**
     * Create a expense
     */
    ExpenseRequestDTO createExpense(ExpenseRequestDTO request);

    ExpenseRequestDTO updateExpense(UUID id, ExpenseRequestDTO request);

    void deleteExpense(UUID id);
}
