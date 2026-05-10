package com.ticketflow.booking.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ticketflow.booking.dto.response.InventoryResponse;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryResponse getInventory(Long eventId) {
        RestTemplate restTemplate = new RestTemplate();
        String inventoryGetEventApiEndpoint = inventoryServiceUrl + "/inventory/event/" + eventId;
        System.out.println("invenotry get event id endpoint: " + inventoryGetEventApiEndpoint);
        return restTemplate.getForObject(inventoryGetEventApiEndpoint, InventoryResponse.class);
    }

}
