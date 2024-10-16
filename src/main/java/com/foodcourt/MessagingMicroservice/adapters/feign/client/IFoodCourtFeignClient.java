package com.foodcourt.MessagingMicroservice.adapters.feign.client;
import com.foodcourt.MessagingMicroservice.adapters.util.AdaptersConstants;

import com.foodcourt.MessagingMicroservice.configuration.feign.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = AdaptersConstants.FOOD_COURT_CLIENT_NAME,
        url = AdaptersConstants.FOOD_COURT_CLIENT_URL,
        configuration = FeignInterceptor.class)
public interface IFoodCourtFeignClient {
    @PostMapping(value = AdaptersConstants.FOOD_COURT_UPDATE_ORDER_STATUS_URL)
    void updateOrderStatus(@PathVariable Long orderId, @RequestParam String status);
    @GetMapping(value = AdaptersConstants.FOOD_COURT_GET_CUSTOMER_ID_FROM_ORDER_URL)
    Long getCustomerIdFromOrder(@PathVariable Long orderId);
    @PostMapping(value = AdaptersConstants.FOOD_COURT_CANCEL_ORDER_URL)
    Integer cancelOrder(@PathVariable Long orderId);
}
