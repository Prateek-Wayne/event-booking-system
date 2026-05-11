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
        try {
            log.info("ORDER_EVENT customerId={} eventId={} ticketCount={}", bookingResponse.getCustomerId(),
                    bookingResponse.getEventId(), bookingResponse.getTicketCount());

            Order order = new Order();
            order.setTotalPrice(bookingResponse.getTotalPrice());
            order.setTicketCount(bookingResponse.getTicketCount());
            order.setCustomerId(bookingResponse.getCustomerId());
            order.setEventId(bookingResponse.getEventId());

            inventoryServiceClient.updateInventory(order.getEventId(), order.getTicketCount());
            orderRepository.saveAndFlush(order);

            log.info("ORDER_EVENT success customerId={} eventId={} ticketCount={}", order.getCustomerId(),
                    order.getEventId(), order.getTicketCount());
        } catch (Exception exception) {
            log.error("ORDER_EVENT failed customerId={} eventId={} ticketCount={}", bookingResponse.getCustomerId(),
                    bookingResponse.getEventId(), bookingResponse.getTicketCount(), exception);
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

}
