package com.foodcourt.MessagingMicroservice.domain.api.usecase;

import com.foodcourt.MessagingMicroservice.domain.api.INotifyServicePort;
import com.foodcourt.MessagingMicroservice.domain.exception.CustomerNotFoundException;
import com.foodcourt.MessagingMicroservice.domain.exception.InvalidPinException;
import com.foodcourt.MessagingMicroservice.domain.exception.UserNotValidException;
import com.foodcourt.MessagingMicroservice.domain.model.Notify;
import com.foodcourt.MessagingMicroservice.domain.spi.IMessagePort;
import com.foodcourt.MessagingMicroservice.domain.spi.INotifyPersistencePort;
import com.foodcourt.MessagingMicroservice.domain.spi.IOrderPort;
import com.foodcourt.MessagingMicroservice.domain.spi.IUserPort;
import com.foodcourt.MessagingMicroservice.domain.util.Constants;
import com.foodcourt.MessagingMicroservice.domain.util.MessageBuilder;

import java.util.Objects;

public class NotifyUseCase implements INotifyServicePort {
    private final IUserPort userPort;
    private final IOrderPort orderPort;
    private final INotifyPersistencePort notifyPersistencePort;
    private final IMessagePort messagePort;

    public NotifyUseCase(IUserPort userPort, IOrderPort orderPort, INotifyPersistencePort notifyPersistencePort, IMessagePort messagePort) {
        this.userPort = userPort;
        this.orderPort = orderPort;
        this.notifyPersistencePort = notifyPersistencePort;
        this.messagePort = messagePort;
    }

    @Override
    public void notifyOrderReady(Long orderId) {
        Long customerId = orderPort.getCustomerIdFromOrder(orderId);
        String phoneNumber = userPort.getCustomerPhoneNumberById(customerId);
        if(customerId == null){
            throw new CustomerNotFoundException(Constants.CUSTOMER_NOT_FOUND);
        }
        orderPort.updateOrderStatus(orderId, Constants.READY_STATUS);
        String message = String.valueOf(MessageBuilder.buildMessage(phoneNumber));
        messagePort.sendMessage(phoneNumber, message);

        Notify notify = new Notify(phoneNumber, message);
        notifyPersistencePort.saveNotify(notify);
    }

    @Override
    public void notifyOrderCompleted(Long orderId, String securityPin) {
        Long customerId = orderPort.getCustomerIdFromOrder(orderId);

        String phoneNumber = userPort.getCustomerPhoneNumberById(customerId);

        if (notifyPersistencePort.isPinAndPhoneValid(securityPin, phoneNumber)) {
            orderPort.updateOrderStatus(orderId, Constants.DELIVERED_STATUS);
            messagePort.sendMessage(phoneNumber, Constants.ORDER_DELIVERED_MESSAGE_CONFIRMATION);
        } else {
            throw new InvalidPinException(Constants.INVALID_PIN_ERROR_MESSAGE);
        }
    }

    @Override
    public void cancelOrder(Long orderId, Long userId) {
        Long customerId = orderPort.getCustomerIdFromOrder(orderId);
        if(!Objects.equals(userId, customerId)){
            throw new UserNotValidException(Constants.USER_NOT_OWNER_OF_ERROR_MESSAGE);
        }
        String phoneNumber = userPort.getCustomerPhoneNumberById(userId);
        Integer cancelFlag = orderPort.cancelOrder(orderId);

        if(Objects.equals(cancelFlag, Constants.CANCELLATION_WENT_THROUGH_FLAG)){
            messagePort.sendMessage(phoneNumber, Constants.ORDER_CANCELLED_MESSAGE);
        } else if(Objects.equals(cancelFlag, Constants.CANCELLATION_DID_NOT_GO_THROUGH_FLAG)){
            messagePort.sendMessage(phoneNumber, Constants.ORDER_CANNOT_BE_CANCELLED_MESSAGE);
        }
    }
}


