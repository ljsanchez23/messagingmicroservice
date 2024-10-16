package com.foodcourt.MessagingMicroservice.adapters.feign.adapter;

import com.foodcourt.MessagingMicroservice.adapters.feign.client.IUserFeignClient;
import com.foodcourt.MessagingMicroservice.domain.spi.IUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter implements IUserPort {
    private final IUserFeignClient userFeignClient;

    @Override
    public String getCustomerPhoneNumberById(Long userId) {
        return userFeignClient.getPhoneById(userId);
    }
}
