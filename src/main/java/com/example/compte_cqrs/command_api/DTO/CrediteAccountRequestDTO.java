package com.example.compte_cqrs.command_api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrediteAccountRequestDTO {
    private String accountId;
    private double amount;
    private String currency;
}
