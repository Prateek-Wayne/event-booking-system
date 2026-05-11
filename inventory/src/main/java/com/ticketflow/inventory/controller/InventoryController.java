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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/inventory")
@Slf4j
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/events")
    public List<Event> getEvents() {
        try {
            log.info("GET /inventory/events");
            List<Event> allEvents = inventoryService.getALlEvents();
            log.info("GET /inventory/events success count={}", allEvents.size());
            return allEvents;
        } catch (Exception exception) {
            log.error("GET /inventory/events failed", exception);
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @GetMapping("/event/{eventId}")
    public Event getEvent(@PathVariable("eventId") Long eventId) {
        try {
            log.info("GET /inventory/event/{}", eventId);
            Event event = inventoryService.getEvent(eventId);
            log.info("GET /inventory/event/{} success", eventId);
            return event;
        } catch (Exception exception) {
            log.error("GET /inventory/event/{} failed", eventId, exception);
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @PutMapping("/event/{eventId}/capacity/{capacity}")
    public Event updateEvent(@PathVariable("eventId") Long eventId, @PathVariable("capacity") Long capacity) {
        try {
            log.info("PUT /inventory/event/{}/capacity/{}", eventId, capacity);
            Event event = inventoryService.updateEventCapacity(eventId, capacity);
            log.info("PUT /inventory/event/{}/capacity/{} success", eventId, capacity);
            return event;
        } catch (Exception exception) {
            log.error("PUT /inventory/event/{}/capacity/{} failed", eventId, capacity, exception);
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @PostMapping("/addEvent")
    public Event addNewEvent(@RequestBody CreateEventRequest entityRequestBody) {
        try {
            log.info("POST /inventory/addEvent venueId={} name={}", entityRequestBody.getVenueId(),
                    entityRequestBody.getName());
            Event event = inventoryService.addEvent(entityRequestBody);
            log.info("POST /inventory/addEvent success eventId={}", event.getId());
            return event;
        } catch (Exception exception) {
            log.error("POST /inventory/addEvent failed venueId={} name={}", entityRequestBody.getVenueId(),
                    entityRequestBody.getName(), exception);
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @PostMapping("/addVenue")
    public void addNewVenue(@RequestBody CreateVenueRequest venueRequestBody) {
        try {
            log.info("POST /inventory/addVenue name={}", venueRequestBody.getName());
            inventoryService.addVenue(venueRequestBody);
            log.info("POST /inventory/addVenue success name={}", venueRequestBody.getName());
        } catch (Exception exception) {
            log.error("POST /inventory/addVenue failed name={}", venueRequestBody.getName(), exception);
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
