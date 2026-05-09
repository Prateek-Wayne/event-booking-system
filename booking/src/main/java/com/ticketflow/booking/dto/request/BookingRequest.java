package com.ticketflow.booking.dto.request;

import lombok.Data;

@Data
public class BookingRequest {
    private Long customerId;
    private Long eventId;
    private Long ticketCount;
}
