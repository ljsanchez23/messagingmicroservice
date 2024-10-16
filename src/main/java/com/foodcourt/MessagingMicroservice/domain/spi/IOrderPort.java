package com.foodcourt.MessagingMicroservice.domain.spi;

public interface IOrderPort {
    void updateOrderStatus(Long orderId, String status);
    Long getCustomerIdFromOrder(Long orderId);
}
