package com.ticketflow.booking.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long customerId;
    private Long eventId;
    private Long ticketCount;
    private BigDecimal totalPrice;
}
