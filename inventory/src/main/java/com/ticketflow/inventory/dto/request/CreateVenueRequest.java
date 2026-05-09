package com.ticketflow.inventory.dto.request;

import lombok.Data;

@Data
public class CreateVenueRequest {
    private String name;
    private String address;
    private Long totalCapacity;
}
