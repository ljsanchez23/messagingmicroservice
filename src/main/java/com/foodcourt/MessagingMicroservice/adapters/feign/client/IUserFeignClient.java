package com.foodcourt.MessagingMicroservice.adapters.feign.client;
import com.foodcourt.MessagingMicroservice.adapters.util.AdaptersConstants;

import com.foodcourt.MessagingMicroservice.configuration.feign.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = AdaptersConstants.USER_CLIENT_NAME,
        url = AdaptersConstants.USER_CLIENT_URL,
        configuration = FeignInterceptor.class)
public interface IUserFeignClient {
    @GetMapping(value = AdaptersConstants.GET_PHONE_BY_ID_URL)
    String getPhoneById(@PathVariable Long id);
}
