package com.mateofiorotto.pocketcare.service.expense;

import com.mateofiorotto.pocketcare.dto.expense.ExpenseRequestDTO;
import com.mateofiorotto.pocketcare.dto.expense.ExpenseResponseDTO;
import com.mateofiorotto.pocketcare.entity.Expense;
import com.mateofiorotto.pocketcare.entity.UserSec;
import com.mateofiorotto.pocketcare.repository.IExpenseRepository;
import com.mateofiorotto.pocketcare.repository.IUserSecRepository;
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
    private final IUserSecRepository userSecRepository;

    public ExpenseService(IExpenseRepository expenseRepository, ModelMapper modelMapper, IUserSecRepository userSecRepository) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
        this.userSecRepository = userSecRepository;
    }

    @Override
    public List<ExpenseResponseDTO> getExpensesList() {
        return expenseRepository.findAll().stream()
                .map(expense -> modelMapper.map(expense, ExpenseResponseDTO.class))
                .toList();
    }

    @Override
    public ExpenseResponseDTO getExpenseById(UUID id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        return modelMapper.map(expense, ExpenseResponseDTO.class);
    }

    @Override
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();

        UserSec currentUser = userSecRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        Expense expense = modelMapper.map(request, Expense.class);

        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());
        expense.setOwner(currentUser);
        System.out.println(expense);
        Expense savedExpense = expenseRepository.save(expense);

        return modelMapper.map(savedExpense, ExpenseResponseDTO.class);
    }

    @Override
    public ExpenseResponseDTO updateExpense(UUID id, ExpenseRequestDTO request) {
        Expense expenseUpdate = expenseRepository.findById(id)
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
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with id: " + id);
        }

        expenseRepository.deleteById(id);
    }
}