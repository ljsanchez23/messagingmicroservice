package com.foodcourt.MessagingMicroservice.adapters.driving.controller;
import com.foodcourt.MessagingMicroservice.adapters.util.AdaptersConstants;

import com.foodcourt.MessagingMicroservice.domain.api.INotifyServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping(AdaptersConstants.NOTIFY_DELIVERED_ENDPOINT)
    public ResponseEntity<Void> notifyOrderCompleted(@PathVariable Long orderId, @RequestParam String securityPin){
        notifyServicePort.notifyOrderCompleted(orderId, securityPin);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
