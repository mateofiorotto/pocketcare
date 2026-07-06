package com.mateofiorotto.pocketcare.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterRequestDTO(@NotBlank @Email String email,
                                     @NotBlank @Size(min = 8, max = 15, message = "The password must be between 8 and 15 characters")  String password) {
}
