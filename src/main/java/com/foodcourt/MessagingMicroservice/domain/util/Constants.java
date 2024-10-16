package com.foodcourt.MessagingMicroservice.domain.util;

public class Constants {
    public static final String ORDER_READY_MESSAGE_PREFIX = "Your order is ready. Your security PIN is: %s";
    public static final String READY_STATUS = "ready";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";
    public static final int FOR_INIT_NUMBER = 0;
    public static final int FOR_FINISH_NUMBER = 5;
    public static final int DEFAULT_MULTIPLIER = 10;
    public static final String DELIVERED_STATUS = "delivered";
    public static final String ORDER_DELIVERED_MESSAGE_CONFIRMATION = "Your order has been delivered, thank you for shopping with us!";
    public static final String INVALID_PIN_ERROR_MESSAGE = "The pin is not valid, please try again";
    public static final String USER_NOT_OWNER_OF_ERROR_MESSAGE = "This user is not the owner of the order";
    public static final String ORDER_CANCELLED_MESSAGE = "Your order has been successfully cancelled.";
    public static final String ORDER_CANNOT_BE_CANCELLED_MESSAGE = "We're sorry, your order is no longer pending and it " +
            "couldn't be cancelled";
    public static final Integer CANCELLATION_WENT_THROUGH_FLAG = 1;
    public static final Integer CANCELLATION_DID_NOT_GO_THROUGH_FLAG = 0;
}
