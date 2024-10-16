package com.foodcourt.MessagingMicroservice.adapters.driving.controller;
import com.foodcourt.MessagingMicroservice.adapters.util.AdaptersConstants;

import com.foodcourt.MessagingMicroservice.configuration.security.jwt.JwtValidate;
import com.foodcourt.MessagingMicroservice.domain.api.INotifyServicePort;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdaptersConstants.NOTIFY_CONTROLLER_URL)
@RequiredArgsConstructor
public class NotifyController {
    private final INotifyServicePort notifyServicePort;

    @Operation(summary = AdaptersConstants.NOTIFY_READY_ENDPOINT_SUMMARY, description = AdaptersConstants.NOTIFY_READY_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.OK, description = AdaptersConstants.NOTIFY_READY_OK_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.NOTIFY_READY_BAD_REQUEST_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.UNAUTHORIZED, description = AdaptersConstants.NOTIFY_READY_UNAUTHORIZED_DESCRIPTION)
    })
    @PostMapping(AdaptersConstants.NOTIFY_READY_ENDPOINT)
    public ResponseEntity<Void> notifyOrderReady(@PathVariable Long orderId){
        notifyServicePort.notifyOrderReady(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = AdaptersConstants.NOTIFY_DELIVERED_ENDPOINT_ENDPOINT_SUMMARY, description = AdaptersConstants.NOTIFY_DELIVERED_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.OK, description = AdaptersConstants.NOTIFY_DELIVERED_OK_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.NOTIFY_DELIVERED_BAD_REQUEST_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.UNAUTHORIZED, description = AdaptersConstants.NOTIFY_DELIVERED_UNAUTHORIZED_DESCRIPTION)
    })
    @PostMapping(AdaptersConstants.NOTIFY_DELIVERED_ENDPOINT)
    public ResponseEntity<Void> notifyOrderCompleted(@PathVariable Long orderId, @RequestParam String securityPin){
        notifyServicePort.notifyOrderCompleted(orderId, securityPin);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = AdaptersConstants.NOTIFY_CANCEL_ENDPOINT_ENDPOINT_SUMMARY, description = AdaptersConstants.NOTIFY_CANCEL_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.OK, description = AdaptersConstants.NOTIFY_CANCEL_OK_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.NOTIFY_CANCEL_BAD_REQUEST_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.UNAUTHORIZED, description = AdaptersConstants.NOTIFY_CANCEL_UNAUTHORIZED_DESCRIPTION)
    })
    @PostMapping(AdaptersConstants.NOTIFY_CANCEL_REQUEST_ENDPOINT)
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId, HttpServletRequest request) {
        Claims claims = JwtValidate.JwtValidation(request);
        Long userId = claims.get(AdaptersConstants.USER_ID_FROM_TOKEN, Long.class);
        notifyServicePort.cancelOrder(orderId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
