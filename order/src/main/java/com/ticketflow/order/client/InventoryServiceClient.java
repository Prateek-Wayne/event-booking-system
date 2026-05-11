package com.ticketflow.order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public ResponseEntity<Void> updateInventory(final Long eventId,
            final Long ticketCount) {
        try {
            String inventoryPutEventApiEndpoint = inventoryServiceUrl + "/inventory/event/" + eventId + "/capacity/"
                    + ticketCount;
            log.info("PUT {}", inventoryPutEventApiEndpoint);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(inventoryPutEventApiEndpoint, null);
            log.info("PUT inventory success eventId={} ticketCount={}", eventId, ticketCount);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            log.error("PUT inventory failed eventId={} ticketCount={}", eventId, ticketCount, exception);
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

}
