package com.ticketflow.booking.dto.response;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {
    private Long customerId;
    private Long eventId;
    private Long ticketCount;
    private BigDecimal totalPrice;
}
