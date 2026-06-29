package com.mateofiorotto.pocketcare.controller;

import com.mateofiorotto.pocketcare.dto.expense.ExpenseRequestDTO;
import com.mateofiorotto.pocketcare.dto.expense.ExpenseResponseDTO;
import com.mateofiorotto.pocketcare.service.expense.IExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final IExpenseService expenseService;

    public ExpenseController(IExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesList(){
        return ResponseEntity.ok(expenseService.getExpensesList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable UUID id){
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PostMapping("/")
    public ResponseEntity<String> saveExpense(@RequestBody ExpenseRequestDTO expense){

        expenseService.createExpense(expense);

        return ResponseEntity.ok("Expense saved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> saveExpense(@PathVariable UUID id, @RequestBody ExpenseRequestDTO expense){

        expenseService.updateExpense(id, expense);

        return ResponseEntity.ok("Expense updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable UUID id){

        expenseService.deleteExpense(id);

        return ResponseEntity.ok("Expense deleted successfully");
    }
}
