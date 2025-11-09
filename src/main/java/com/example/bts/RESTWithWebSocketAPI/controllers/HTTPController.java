package com.example.bts.RESTWithWebSocketAPI.controllers;

import com.example.bts.RESTWithWebSocketAPI.models.GeolocationModel;
import com.example.bts.RESTWithWebSocketAPI.models.HTTPEndpoints;
import com.example.bts.RESTWithWebSocketAPI.models.OperationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class HTTPController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping(HTTPEndpoints.SEND_LOCATION)
    public GeolocationModel sendLocation(@RequestBody GeolocationModel payload,@PathVariable String route) {
        GeolocationModel model = new GeolocationModel();
        model.setTimeStamp(LocalDateTime.now().toString());
        model.setLongitude(payload.getLongitude());
        model.setLatitude(payload.getLatitude());
        String destination = String.format("/broadcast/location/to/%s",route);
        System.out.println(destination);
        simpMessagingTemplate.convertAndSend(destination, model);
        return model;
    }

    @GetMapping(HTTPEndpoints.CLOSE_CONNECTION)
    public OperationStatus closeSocketConnection(@PathVariable String route) {
        String destination = String.format("/connection/close/for/%s",route);
        System.out.println(destination);
        simpMessagingTemplate.convertAndSend(destination, true);
        return new OperationStatus(true);
    }
}
