package com.foodcourt.MessagingMicroservice.adapters.driven.twilio;

import com.foodcourt.MessagingMicroservice.domain.spi.IMessagePort;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class MessageAdapter implements IMessagePort {
    private String accountSid = System.getenv("TWILIO_ACCOUNT_SID");
    private String authToken = System.getenv("TWILIO_AUTH_TOKEN");
    private String twilioPhoneNumber = System.getenv("TWILIO_PHONE_NUMBER");

    public MessageAdapter() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendMessage(String phoneNumber, String message) {
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                message
        ).create();
    }
}
