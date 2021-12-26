package com.example.compte_cqrs.command_api.events;

import lombok.Getter;

public class AccountCreditedEvent extends BaseEvent<String> {

    @Getter private double amount;
    @Getter private String currency;
    public AccountCreditedEvent(String id, Double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
