package com.foodcourt.MessagingMicroservice.domain.util;
import com.foodcourt.MessagingMicroservice.domain.model.Notify;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

class MessageBuilderTest {
    @Test
    void buildMessage_shouldReturnNotifyWithCorrectMessageFormat() {
        String phoneNumber = "+1234567890";
        String mockPin = "123456";
        String expectedMessageContent = String.format(Constants.ORDER_READY_MESSAGE_PREFIX, mockPin);

        try (MockedStatic<MessageBuilder> mockedStatic = mockStatic(MessageBuilder.class, CALLS_REAL_METHODS)) {
            mockedStatic.when(MessageBuilder::securityPinGenerator).thenReturn(mockPin);

            Notify notify = MessageBuilder.buildMessage(phoneNumber);

            assertNotNull(notify);
            assertEquals(phoneNumber, notify.getPhoneNumber());
            assertEquals(expectedMessageContent, notify.getMessage());
        }
    }
}
