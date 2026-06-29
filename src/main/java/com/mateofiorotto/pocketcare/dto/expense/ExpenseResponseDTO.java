package com.mateofiorotto.pocketcare.dto.expense;

import java.time.LocalDate;
import java.util.UUID;

public record ExpenseResponseDTO (
    UUID id,
    String name,
    double amount,
    LocalDate date
){}
