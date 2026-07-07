package com.mateofiorotto.pocketcare.dto.expense;

import com.mateofiorotto.pocketcare.entity.Category;
import com.mateofiorotto.pocketcare.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDTO {
    private UUID id;
    private String name;
    private double amount;
    private Currency currency;
    private LocalDate date;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}