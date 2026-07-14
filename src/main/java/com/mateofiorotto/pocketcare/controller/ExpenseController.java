package com.mateofiorotto.pocketcare.controller;

import com.mateofiorotto.pocketcare.dto.expense.ExpenseRequestDTO;
import com.mateofiorotto.pocketcare.dto.expense.ExpenseResponseDTO;
import com.mateofiorotto.pocketcare.service.expense.IExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
@PreAuthorize("denyAll()")
public class ExpenseController {
    private final IExpenseService expenseService;

    public ExpenseController(IExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesList(){
        return ResponseEntity.ok(expenseService.getExpensesList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable UUID id){
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @GetMapping("/total")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<BigDecimal> countExpensesByUserAuthenticated(){
        return ResponseEntity.ok(expenseService.countExpensesByUserAuthenticated());
    }

    @GetMapping("/total/{category}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<BigDecimal> countExpensesByUserAuthenticatedAndCategory(@PathVariable String category){
        return ResponseEntity.ok(expenseService.countExpensesByUserAuthenticatedAndCategory(category));
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ExpenseResponseDTO>> findExpensesByCategoryAndUserAuthenticated(@PathVariable String category){
        return ResponseEntity.ok(expenseService.findExpensesByCategoryAndUserAuthenticated(category));
    }

    @GetMapping(value = "/search", params = "name")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ExpenseResponseDTO>> findExpensesByName(
            @RequestParam String name) {

        return ResponseEntity.ok(expenseService.findExpensesByNameAndUserAuthenticated(name));
    }

    @GetMapping(value = "/search", params = "date")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ExpenseResponseDTO>> findExpensesByDate(
            @RequestParam LocalDate date) {

        return ResponseEntity.ok(expenseService.findExpensesByDateAndUserAuthenticated(date));
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> saveExpense(@Valid @RequestBody ExpenseRequestDTO expense){

        expenseService.createExpense(expense);

        return ResponseEntity.ok("Expense saved successfully");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> saveExpense(@PathVariable UUID id, @Valid @RequestBody ExpenseRequestDTO expense){

        expenseService.updateExpense(id, expense);

        return ResponseEntity.ok("Expense updated successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> deleteExpense(@PathVariable UUID id){

        expenseService.deleteExpense(id);

        return ResponseEntity.ok("Expense deleted successfully");
    }
}
