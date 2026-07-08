package com.mateofiorotto.pocketcare.service.expense;

import com.mateofiorotto.pocketcare.dto.expense.ExpenseRequestDTO;
import com.mateofiorotto.pocketcare.dto.expense.ExpenseResponseDTO;
import com.mateofiorotto.pocketcare.entity.Expense;
import com.mateofiorotto.pocketcare.entity.UserSec;
import com.mateofiorotto.pocketcare.repository.IExpenseRepository;
import com.mateofiorotto.pocketcare.repository.IUserSecRepository;
import com.mateofiorotto.pocketcare.service.usersec.IUserSecService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService implements IExpenseService {
    private final IExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;
    private final IUserSecService userSecService;

    public ExpenseService(IExpenseRepository expenseRepository, ModelMapper modelMapper, IUserSecService userSecService) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
        this.userSecService = userSecService;
    }

    @Override
    public List<ExpenseResponseDTO> getExpensesList() {
        return expenseRepository.findListExpensesByUserAuthenticated(
                userSecService.findAuthenticatedUser().getId()).stream()
                .map(expense -> modelMapper.map(expense, ExpenseResponseDTO.class))
                .toList();
    }

    @Override
    public ExpenseResponseDTO getExpenseById(UUID id) {
        Expense expense = expenseRepository.findExpenseByUserAuthenticated(id, userSecService.findAuthenticatedUser().getId())
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        return modelMapper.map(expense, ExpenseResponseDTO.class);
    }

    @Override
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO request) {
        UserSec currentUser = userSecService.findAuthenticatedUser();

        Expense expense = modelMapper.map(request, Expense.class);

        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());
        expense.setOwner(currentUser);

        Expense savedExpense = expenseRepository.save(expense);

        return modelMapper.map(savedExpense, ExpenseResponseDTO.class);
    }

    @Override
    public ExpenseResponseDTO updateExpense(UUID id, ExpenseRequestDTO request) {
        Expense expenseUpdate = expenseRepository.findExpenseByUserAuthenticated(id, userSecService.findAuthenticatedUser().getId())
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        expenseUpdate.setName(request.getName());
        expenseUpdate.setAmount(request.getAmount());
        expenseUpdate.setCurrency(request.getCurrency());
        expenseUpdate.setDate(request.getDate());
        expenseUpdate.setUpdatedAt(LocalDateTime.now());
        expenseUpdate.setCategory(request.getCategory());

        Expense updatedExpense = expenseRepository.save(expenseUpdate);

        return modelMapper.map(updatedExpense, ExpenseResponseDTO.class);
    }

    @Override
    public void deleteExpense(UUID id) {
        Expense expenseDelete = expenseRepository.findExpenseByUserAuthenticated(id, userSecService.findAuthenticatedUser().getId())
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        expenseRepository.delete(expenseDelete);
    }
}