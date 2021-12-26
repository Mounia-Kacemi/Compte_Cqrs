package com.example.compte_cqrs.commands.aggregates;

import com.example.compte_cqrs.command_api.commands.CreateAccountCommand;
import com.example.compte_cqrs.command_api.commands.CreditAccountCommand;
import com.example.compte_cqrs.command_api.enums.AccountStatus;
import com.example.compte_cqrs.command_api.events.AccountActivatedEvent;
import com.example.compte_cqrs.command_api.events.AccountCreatedEvent;
import com.example.compte_cqrs.command_api.events.AccountCreditedEvent;
import com.example.compte_cqrs.command_api.exception.AmountNegativeException;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        if(createAccountCommand.getInitialBalance()<0)
            throw new RuntimeException("Impossible <un solde negative>");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent){
        this.currency=accountCreatedEvent.getCurrency();
        this.balance=accountCreatedEvent.getInitialBalance();
        this.status=AccountStatus.CREATED;
        this.accountId=accountCreatedEvent.getId();
       AggregateLifecycle.apply(new AccountActivatedEvent(
                accountCreatedEvent.getId(),
                AccountStatus.ACTIVATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.status=event.getStatus();
    }
    @CommandHandler
    public void handle(CreditAccountCommand command){
        if(command.getAmount()<0) throw new AmountNegativeException("Amount should not be negative");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        this.balance += event.getAmount();
    }

}
