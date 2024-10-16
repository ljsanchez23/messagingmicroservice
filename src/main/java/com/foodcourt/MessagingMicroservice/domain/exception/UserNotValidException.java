package com.foodcourt.MessagingMicroservice.domain.exception;

public class UserNotValidException extends RuntimeException{
    public UserNotValidException(String message){
        super(message);
    }
}
