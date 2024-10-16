package com.foodcourt.MessagingMicroservice.domain.spi;

public interface IMessagePort {
    void sendMessage(String phoneNumber, String message);
}
