package com.example.compte_cqrs.command_api.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
    @Getter private T id;


    public BaseCommand(T id) {
        this.id = id;
    }
}
