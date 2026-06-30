package com.mateofiorotto.pocketcare.dto.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDTO {
    private UUID id;
    private String name;
    private double amount;
    private LocalDate date;
}
