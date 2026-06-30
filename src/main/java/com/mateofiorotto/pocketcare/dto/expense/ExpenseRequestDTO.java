package com.mateofiorotto.pocketcare.dto.expense;

import com.mateofiorotto.pocketcare.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDTO {
    private String name;
    private double amount;
    private LocalDate date;
    private Category category;
}