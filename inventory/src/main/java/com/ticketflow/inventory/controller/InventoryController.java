package com.ticketflow.inventory.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketflow.inventory.dto.request.CreateEventRequest;
import com.ticketflow.inventory.dto.request.CreateVenueRequest;
import com.ticketflow.inventory.entity.Event;
import com.ticketflow.inventory.service.InventoryService;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/events")
    public List<Event> getMethodName() {
        List<Event> allEvents = inventoryService.getALlEvents();
        return allEvents;
    }

    @PostMapping("/addEvent")
    public Event addNewEvent(@RequestBody CreateEventRequest entityRequestBody) {
        Event event = inventoryService.addEvent(entityRequestBody);

        return event;
    }

    @PostMapping("/addVenue")
    public void addNewVenue(@RequestBody CreateVenueRequest venueRequestBody) {
        inventoryService.addVenue(venueRequestBody);
    }

}
