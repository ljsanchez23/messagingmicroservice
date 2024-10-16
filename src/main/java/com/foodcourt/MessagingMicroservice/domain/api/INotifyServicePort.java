package com.foodcourt.MessagingMicroservice.domain.api;

public interface INotifyServicePort {
    void notifyOrderReady(Long orderId);
    void notifyOrderCompleted(Long orderId, String securityPin);
    void cancelOrder(Long orderId, Long userId);
}
