package com.foodcourt.MessagingMicroservice.domain.api.usecase;

import com.foodcourt.MessagingMicroservice.domain.exception.CustomerNotFoundException;
import com.foodcourt.MessagingMicroservice.domain.exception.InvalidPinException;
import com.foodcourt.MessagingMicroservice.domain.model.Notify;
import com.foodcourt.MessagingMicroservice.domain.spi.IMessagePort;
import com.foodcourt.MessagingMicroservice.domain.spi.INotifyPersistencePort;
import com.foodcourt.MessagingMicroservice.domain.spi.IOrderPort;
import com.foodcourt.MessagingMicroservice.domain.spi.IUserPort;
import com.foodcourt.MessagingMicroservice.domain.util.Constants;
import com.foodcourt.MessagingMicroservice.domain.util.MessageBuilder;
import com.foodcourt.MessagingMicroservice.util.TestConstants;
import com.foodcourt.MessagingMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotifyUseCaseTest {

    @Mock
    private IUserPort userPort;

    @Mock
    private IOrderPort orderPort;

    @Mock
    private INotifyPersistencePort notifyPersistencePort;

    @Mock
    private IMessagePort messagePort;

    @InjectMocks
    private NotifyUseCase notifyUseCase;

    @Test
    @DisplayName(TestConstants.SHOULD_UPDATE_STATUS_WHEN_PHONE_NUMBER_EXISTS)
    void shouldUpdateStatusAndSendMessageWhenPhoneNumberExists() {

        Notify expectedNotify = TestDataFactory.createDefaultNotify();

        when(orderPort.getCustomerIdFromOrder(TestConstants.ORDER_ID)).thenReturn(TestConstants.CUSTOMER_ID);
        when(userPort.getCustomerPhoneNumberById(TestConstants.CUSTOMER_ID)).thenReturn(TestConstants.PHONE_NUMBER);

        try (MockedStatic<MessageBuilder> mockedStatic = mockStatic(MessageBuilder.class)) {
            mockedStatic.when(() -> MessageBuilder.buildMessage(TestConstants.PHONE_NUMBER)).thenReturn(expectedNotify);

            notifyUseCase.notifyOrderReady(TestConstants.ORDER_ID);

            ArgumentCaptor<Notify> notifyCaptor = ArgumentCaptor.forClass(Notify.class);
            verify(notifyPersistencePort).saveNotify(notifyCaptor.capture());

            Notify actualNotify = notifyCaptor.getValue();
            assertEquals(expectedNotify.getPhoneNumber(), actualNotify.getPhoneNumber());
            assertEquals(expectedNotify.getMessage(), actualNotify.getMessage());
        }
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_CUSTOMER_NOT_FOUND)
    void shouldThrowCustomerNotFoundExceptionWhenCustomerDoesNotExist() {

        when(orderPort.getCustomerIdFromOrder(TestConstants.ORDER_ID)).thenReturn(null);

        CustomerNotFoundException exception = assertThrows(
                CustomerNotFoundException.class,
                () -> notifyUseCase.notifyOrderReady(TestConstants.ORDER_ID)
        );

        assertEquals(Constants.CUSTOMER_NOT_FOUND, exception.getMessage());

        verify(orderPort, never()).updateOrderStatus(anyLong(), anyString());
        verify(messagePort, never()).sendMessage(anyString(), anyString());
        verify(notifyPersistencePort, never()).saveNotify(any(Notify.class));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_UPDATE_STATUS_WHEN_PARAMS_ARE_VALID)
    void shouldUpdateStatusAndSendMessageWhenPinAndPhoneNumberAreValid() {
        Long orderId = TestConstants.ORDER_ID;
        String securityPin = TestConstants.VALID_PIN;
        Long customerId = TestConstants.VALID_CUSTOMER_ID;
        String phoneNumber = TestConstants.VALID_PHONE_NUMBER;

        when(orderPort.getCustomerIdFromOrder(orderId)).thenReturn(customerId);
        when(userPort.getCustomerPhoneNumberById(customerId)).thenReturn(phoneNumber);
        when(notifyPersistencePort.isPinAndPhoneValid(securityPin, phoneNumber)).thenReturn(true);

        notifyUseCase.notifyOrderCompleted(orderId, securityPin);

        verify(orderPort).updateOrderStatus(orderId, Constants.DELIVERED_STATUS);
        verify(messagePort).sendMessage(phoneNumber, Constants.ORDER_DELIVERED_MESSAGE_CONFIRMATION);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_PIN_IS_NOT_VALID)
    void shouldThrowInvalidPinExceptionWhenPinIsInvalid() {
        Long orderId = TestConstants.ORDER_ID;
        String securityPin = TestConstants.VALID_PIN;
        Long customerId = TestConstants.VALID_CUSTOMER_ID;
        String phoneNumber = TestConstants.VALID_PHONE_NUMBER;

        when(orderPort.getCustomerIdFromOrder(orderId)).thenReturn(customerId);
        when(userPort.getCustomerPhoneNumberById(customerId)).thenReturn(phoneNumber);
        when(notifyPersistencePort.isPinAndPhoneValid(securityPin, phoneNumber)).thenReturn(false);

        InvalidPinException exception = assertThrows(
                InvalidPinException.class,
                () -> notifyUseCase.notifyOrderCompleted(orderId, securityPin)
        );

        assertEquals(Constants.INVALID_PIN_ERROR_MESSAGE, exception.getMessage());

        verify(orderPort, never()).updateOrderStatus(anyLong(), anyString());
        verify(messagePort, never()).sendMessage(anyString(), anyString());
    }
}
