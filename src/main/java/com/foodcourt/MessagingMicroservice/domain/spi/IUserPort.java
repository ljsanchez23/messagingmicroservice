package com.foodcourt.MessagingMicroservice.domain.spi;

public interface IUserPort {
    String getCustomerPhoneNumberById(Long userId);
}
