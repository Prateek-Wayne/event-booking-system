package com.ticketflow.order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public ResponseEntity<Void> updateInventory(final Long eventId,
            final Long ticketCount) {
        // event/{eventId}/capacity/{capacity}"
        String inventoryPutEventApiEndpoint = inventoryServiceUrl + "/inventory/event/" + eventId + "/capacity/"
                + ticketCount;
        System.out.println("invenotry get event id endpoint: " + inventoryPutEventApiEndpoint);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(inventoryPutEventApiEndpoint, null);
        return ResponseEntity.ok().build();
    }

}
