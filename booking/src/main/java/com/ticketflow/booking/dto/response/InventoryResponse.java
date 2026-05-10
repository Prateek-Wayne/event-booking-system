package com.ticketflow.booking.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class InventoryResponse {
    private Long id; // was eventId
    private String name; // was event
    private Long leftCapacity;
    private VenueResponse venue;
    private BigDecimal ticketPrice;
    private Long totalCapacity;
}
