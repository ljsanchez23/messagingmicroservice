package com.foodcourt.MessagingMicroservice.domain.api;

public interface INotifyServicePort {
    void notifyOrder(Long orderId);
}
