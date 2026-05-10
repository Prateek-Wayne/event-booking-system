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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/events")
    public List<Event> getEvents() {
        List<Event> allEvents = inventoryService.getALlEvents();
        return allEvents;
    }

    @GetMapping("/event/{eventId}")
    public Event getEvent(@PathVariable("eventId") Long eventId) {
        Event event = inventoryService.getEvent(eventId);
        return event;
    }

    @PutMapping("/inventory/event/{eventId}/capacity/{capacity}")
    public Event updateEvent(@PathVariable("eventId") Long eventId, @PathVariable("capacity") Long capacity) {
        Event event = inventoryService.updateEventCapacity(eventId, capacity);
        return event;
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
