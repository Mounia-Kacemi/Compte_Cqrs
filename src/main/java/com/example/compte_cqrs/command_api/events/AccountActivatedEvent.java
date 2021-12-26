package com.example.compte_cqrs.command_api.events;

import com.example.compte_cqrs.command_api.enums.AccountStatus;
import lombok.Getter;

public class AccountActivatedEvent extends BaseEvent<String>{
    @Getter
    private AccountStatus status;
    public AccountActivatedEvent(String id, AccountStatus status) {
        super(id);
        this.status=status;
    }

}
