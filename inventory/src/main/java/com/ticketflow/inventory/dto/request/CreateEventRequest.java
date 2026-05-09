package com.ticketflow.inventory.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateEventRequest {
    String name;
    Long venueId;
    BigDecimal ticketPrice;
}
