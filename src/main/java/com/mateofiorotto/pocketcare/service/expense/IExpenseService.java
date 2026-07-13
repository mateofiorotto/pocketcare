package com.mateofiorotto.pocketcare.service.expense;

import com.mateofiorotto.pocketcare.dto.expense.ExpenseResponseDTO;
import com.mateofiorotto.pocketcare.dto.expense.ExpenseRequestDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IExpenseService {
    /**
     * Get the list of all own expenses
     *
     * @return
     */
    List<ExpenseResponseDTO> getExpensesList();

    /**
     * Get specific expense by ID
     *
     * @param id
     * @return
     */
    ExpenseResponseDTO getExpenseById(UUID id);

    /**
     * Get the count of expenses by the authenticated user
     *
     * @return
     */
    BigDecimal countExpensesByUserAuthenticated();

    /**
     * Get the total of expenses by category and auth user
     */
    BigDecimal countExpensesByUserAuthenticatedAndCategory(String category);

    /**
     * Create a expense
     */
    ExpenseResponseDTO createExpense(ExpenseRequestDTO request);

    /**
     * Update a expense
     *
     * @param id
     * @param request
     * @return
     */
    ExpenseResponseDTO updateExpense(UUID id, ExpenseRequestDTO request);

    /**
     * Delete expenses by ID
     *
     * @param id
     */
    void deleteExpense(UUID id);
}
