package com.example.compte_cqrs.command_api.events;

import lombok.Getter;

public abstract class BaseEvent<T> {
   @Getter
   private T id;


    protected BaseEvent(T id) {
        this.id = id;
    }
}
