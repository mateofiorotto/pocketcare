package com.mateofiorotto.pocketcare.dto.expense;

import com.mateofiorotto.pocketcare.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDTO {
    @NotBlank(message = "The name can't be blank")
    @Size(max = 100, message = "The name only can have 60 max characters")
    private String name;
    @NotNull(message = "The amount can't be null")
    @Positive(message = "The amount only can be positive")
    private double amount;
    @NotNull(message = "The date can't be null")
    private LocalDate date;
    @NotNull(message = "The category can't be null")
    private Category category;
}