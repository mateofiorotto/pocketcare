package com.mateofiorotto.pocketcare.service.expense;

import com.mateofiorotto.pocketcare.dto.expense.ExpenseRequestDTO;
import com.mateofiorotto.pocketcare.dto.expense.ExpenseResponseDTO;
import com.mateofiorotto.pocketcare.entity.Expense;
import com.mateofiorotto.pocketcare.repository.IExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseService implements IExpenseService {
    private final IExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    public ExpenseService(IExpenseRepository expenseRepository, ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExpenseResponseDTO> getExpensesList() {

        List<ExpenseResponseDTO> expenseList = expenseRepository.findAll().stream()
                .map(expenses -> modelMapper.map(expenses, ExpenseResponseDTO.class))
                .toList();

        return expenseList;
    }

    @Override
    public ExpenseResponseDTO getExpenseById(UUID id) {

        ExpenseResponseDTO expenseById = expenseRepository.findById(id)
                .map(expense -> modelMapper.map(expense, ExpenseResponseDTO.class))
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        return expenseById;
    }

    @Override
    public ExpenseRequestDTO createExpense(ExpenseRequestDTO request) {
        Expense saveExpense = modelMapper.map(request, Expense.class);

        expenseRepository.save(saveExpense);

        return request;
    }

    @Override
    public ExpenseRequestDTO updateExpense(UUID id, ExpenseRequestDTO request) {
        Expense expenseUpdate = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        expenseUpdate.setName(request.getName());
        expenseUpdate.setAmount(request.getAmount());
        expenseUpdate.setDate(request.getDate());
        expenseUpdate.setCategory(request.getCategory());

        expenseRepository.save(expenseUpdate);

        return request;
    }

    @Override
    public void deleteExpense(UUID id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with id: " + id);
        }

        expenseRepository.deleteById(id);
    }
}
