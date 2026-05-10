package com.ticketflow.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ticketflow.booking.dto.response.BookingResponse;
import com.ticketflow.order.client.InventoryServiceClient;
import com.ticketflow.order.entity.Order;
import com.ticketflow.order.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingResponse bookingResponse) {

        log.info("Received order event: {}", bookingResponse);
        Order order = new Order();
        order.setTotalPrice(bookingResponse.getTotalPrice());
        order.setTicketCount(bookingResponse.getTicketCount());
        order.setCustomerId(bookingResponse.getCustomerId());
        order.setEventId(bookingResponse.getEventId());

        // Update Inventory
        inventoryServiceClient.updateInventory(order.getEventId(), order.getTicketCount());
        log.info("Inventory updated for event: {}, less tickets: {}", order.getEventId(), order.getTicketCount());
        // save the order
        orderRepository.saveAndFlush(order);
    }

}
