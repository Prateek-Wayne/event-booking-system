package com.ticketflow.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.ticketflow.inventory.dto.request.CreateEventRequest;
import com.ticketflow.inventory.dto.request.CreateVenueRequest;
import com.ticketflow.inventory.entity.Event;
import com.ticketflow.inventory.entity.Venue;
import com.ticketflow.inventory.repository.EventRepository;
import com.ticketflow.inventory.repository.VenueRepository;

@Service
public class InventoryService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private VenueRepository venueRepository;

    public void addVenue(CreateVenueRequest venueRequest) {
        Venue venue = new Venue(null, venueRequest.getName(), venueRequest.getAddress(),
                venueRequest.getTotalCapacity());

        venueRepository.save(venue);
        return;
    }

    public Event addEvent(CreateEventRequest eventRequest) {
        Optional<Venue> venue = venueRepository.findById(eventRequest.getVenueId());
        if (!venue.isPresent()) {
            throw new RuntimeException("Venue not found");
        }
        Event event = new Event(null, eventRequest.getName(), venue.get().getTotalCapacity(),
                eventRequest.getLeftCapacity(), venue.get());
        return eventRepository.save(event);

    }

    public List<Event> getALlEvents() {
        List<Event> getAllEvents = eventRepository.findAll();
        return getAllEvents;
    }

}
