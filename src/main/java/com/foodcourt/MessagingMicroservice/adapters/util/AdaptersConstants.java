package com.foodcourt.MessagingMicroservice.adapters.util;

public class AdaptersConstants {
    public static final String NOTIFY_CONTROLLER_URL = "/notify";
    public static final String NOTIFY_TABLE_NAME = "notify";
    public static final String NOTIFY_READY_ENDPOINT = "/{orderId}/ready";
    public static final String NOTIFY_CANCEL_REQUEST_ENDPOINT = "{orderId}/cancel";
    public static final String NOTIFY_DELIVERED_ENDPOINT = "/{orderId}/delivered";
    public static final String OK = "200";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String NOTIFY_READY_ENDPOINT_SUMMARY = "This endpoint is used to notify that an order is ready";
    public static final String NOTIFY_READY_ENDPOINT_DESCRIPTION = "A message is being sent through twilio API to the customer's" +
            "phone number";
    public static final String NOTIFY_READY_OK_DESCRIPTION = "If the message is sent a 200 response code will be given";
    public static final String NOTIFY_READY_BAD_REQUEST_DESCRIPTION = "If the customer can't be notified a 400 response code" +
            "will be given";
    public static final String NOTIFY_READY_UNAUTHORIZED_DESCRIPTION = "Only employees can access to this functionality";
    public static final String USER_CLIENT_NAME = "UserMicroservice";
    public static final String USER_CLIENT_URL = "localhost:8080";
    public static final String GET_PHONE_BY_ID_URL = "/user/{id}/phone";
    public static final String FOOD_COURT_CLIENT_NAME = "FoodCourtMicroservice";
    public static final String FOOD_COURT_CLIENT_URL = "localhost:8181";
    public static final String FOOD_COURT_UPDATE_ORDER_STATUS_URL = "/order/{orderId}/update";
    public static final String FOOD_COURT_GET_CUSTOMER_ID_FROM_ORDER_URL = "/order/{orderId}/customer";
    public static final String FOOD_COURT_CANCEL_ORDER_URL = "/order/{orderId}/cancel";
    public static final String NOTIFY_DELIVERED_ENDPOINT_ENDPOINT_SUMMARY = "This endpoint is used when the order is delivered";
    public static final String NOTIFY_DELIVERED_ENDPOINT_DESCRIPTION = "If the request go through the customer will receive the message that the item" +
            "has been delivered";
    public static final String NOTIFY_DELIVERED_OK_DESCRIPTION = "If the process go through a 200 response code will be given";
    public static final String NOTIFY_DELIVERED_BAD_REQUEST_DESCRIPTION = "If the process does not go through a 400 response code will" +
            "be given";
    public static final String NOTIFY_DELIVERED_UNAUTHORIZED_DESCRIPTION = "If the user is not authorized a 401 response code will be given";

    public static final String USER_ID_FROM_TOKEN = "userId";

    public static final String NOTIFY_CANCEL_ENDPOINT_ENDPOINT_SUMMARY = "This endpoint is used to cancel a pending order";
    public static final String NOTIFY_CANCEL_ENDPOINT_DESCRIPTION = "Customers can choose this option if they want to cancel this order";
    public static final String NOTIFY_CANCEL_OK_DESCRIPTION = "If the cancellation goes through a 200 response code will be given";
    public static final String NOTIFY_CANCEL_BAD_REQUEST_DESCRIPTION = "If the cancellation does not go through a 400 response code will be given";
    public static final String NOTIFY_CANCEL_UNAUTHORIZED_DESCRIPTION = "If the user is not authorized a 401 response code will be given";
}
