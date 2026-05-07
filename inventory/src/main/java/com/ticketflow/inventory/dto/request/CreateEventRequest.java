package com.ticketflow.inventory.dto.request;

import lombok.Data;

@Data
public class CreateEventRequest {
    String name;
    Long leftCapacity;
    Long venueId;
}
