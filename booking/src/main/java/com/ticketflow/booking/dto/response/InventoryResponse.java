package com.ticketflow.booking.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class InventoryResponse {
    private Long eventId;
    private String event;
    private Long leftCapacity;
    private VenueResponse venue;
    private BigDecimal ticketPrice;
}
