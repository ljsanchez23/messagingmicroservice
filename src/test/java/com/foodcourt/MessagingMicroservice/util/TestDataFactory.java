package com.foodcourt.MessagingMicroservice.util;

import com.foodcourt.MessagingMicroservice.domain.model.Notify;

public class TestDataFactory {
    public static Notify createDefaultNotify(){
        return new Notify(TestConstants.PHONE_NUMBER, TestConstants.ORDER_READY_MESSAGE);
    }
}
