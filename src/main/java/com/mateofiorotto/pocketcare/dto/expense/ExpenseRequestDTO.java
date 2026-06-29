package com.mateofiorotto.pocketcare.dto.expense;

import java.time.LocalDate;

public record ExpenseRequestDTO(
        String name,
        double amount,
        LocalDate date
){}
