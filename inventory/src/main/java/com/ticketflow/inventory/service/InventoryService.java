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

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
                venue.get().getTotalCapacity(), venue.get(), eventRequest.getTicketPrice());
        return eventRepository.save(event);

    }

    public List<Event> getALlEvents() {
        List<Event> getAllEvents = eventRepository.findAll();
        return getAllEvents;
    }

    public Event getEvent(Long eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event == null)
            throw new RuntimeException("not event exits with this id");
        return event.get();
    }

    public Event updateEventCapacity(Long eventId, Long ticketsBooked) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null)
            throw new RuntimeException("event not found");
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepository.saveAndFlush(event);
        log.info("Updated event capacity for event id: {} with tickets booked: {}", eventId, ticketsBooked);
        return event;

    }

}
