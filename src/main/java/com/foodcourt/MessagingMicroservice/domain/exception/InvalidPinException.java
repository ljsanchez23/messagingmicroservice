package com.foodcourt.MessagingMicroservice.domain.exception;

public class InvalidPinException extends RuntimeException{
    public InvalidPinException(String message){
        super(message);
    }
}
