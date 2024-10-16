package com.foodcourt.MessagingMicroservice.adapters.feign.adapter;

import com.foodcourt.MessagingMicroservice.adapters.feign.client.IFoodCourtFeignClient;
import com.foodcourt.MessagingMicroservice.domain.spi.IOrderPort;


public class FoodCourtAdapter implements IOrderPort {
    private final IFoodCourtFeignClient foodCourtFeignClient;

    public FoodCourtAdapter(IFoodCourtFeignClient foodCourtFeignClient) {
        this.foodCourtFeignClient = foodCourtFeignClient;
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        foodCourtFeignClient.updateOrderStatus(orderId, status);
    }

    @Override
    public Long getCustomerIdFromOrder(Long orderId) {
        return foodCourtFeignClient.getCustomerIdFromOrder(orderId);
    }
}
