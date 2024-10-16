package com.foodcourt.MessagingMicroservice.domain.util;

import com.foodcourt.MessagingMicroservice.domain.model.Notify;

public class MessageBuilder {
    public static Notify buildMessage(String phoneNumber){
        String securityPin = securityPinGenerator();
        String messageContent = String.format(Constants.ORDER_READY_MESSAGE_PREFIX, securityPin);
        return new Notify(phoneNumber, messageContent);
    }

    protected static String securityPinGenerator() {
        StringBuilder pinBuilder = new StringBuilder();
        for (int i = Constants.FOR_INIT_NUMBER; i < Constants.FOR_FINISH_NUMBER; i++) {
            int randomDigit = (int) (Math.random() * Constants.DEFAULT_MULTIPLIER);
            pinBuilder.append(randomDigit);
        }
        return pinBuilder.toString();
    }
}
