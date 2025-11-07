package com.example.bts.RESTWithWebSocketAPI.controllers;

import com.example.bts.RESTWithWebSocketAPI.models.GeolocationModel;
import com.example.bts.RESTWithWebSocketAPI.models.HTTPEndpoints;
import com.example.bts.RESTWithWebSocketAPI.models.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HTTPController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping(HTTPEndpoints.SEND_LOCATION)
    public GeolocationModel sendLocation(@RequestBody GeolocationModel payload) {
        GeolocationModel model = new GeolocationModel();
        model.setTimeStamp(LocalDateTime.now().toString());
        model.setLongitude(payload.getLongitude());
        model.setLatitude(payload.getLatitude());
        simpMessagingTemplate.convertAndSend("/broadcast/location", model);
        return model;
    }

    @GetMapping(HTTPEndpoints.CLOSE_CONNECTION)
    public OperationStatus closeSocketConnection() {
        simpMessagingTemplate.convertAndSend("/connection/close", true);
        return new OperationStatus(true);
    }
}
