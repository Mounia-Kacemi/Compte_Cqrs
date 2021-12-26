package com.example.compte_cqrs.command_api.exception;

public class AmountNegativeException extends RuntimeException {
    public AmountNegativeException(String mssg) {
        super(mssg);
    }
}
