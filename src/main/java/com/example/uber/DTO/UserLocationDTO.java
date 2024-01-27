package com.example.uber.DTO;

import jakarta.validation.constraints.NotNull;

public record UserLocationDTO(@NotNull double latitude,
                              @NotNull double longitude,
                              @NotNull double radius) {
}
