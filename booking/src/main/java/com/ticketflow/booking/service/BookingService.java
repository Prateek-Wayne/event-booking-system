package com.ticketflow.booking.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketflow.booking.client.InventoryServiceClient;
import com.ticketflow.booking.dto.request.BookingRequest;
import com.ticketflow.booking.dto.response.BookingResponse;
import com.ticketflow.booking.dto.response.InventoryResponse;
import com.ticketflow.booking.entity.Customer;
import com.ticketflow.booking.repository.CustomerRepository;

@Service
public class BookingService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    public BookingResponse createBooking(BookingRequest bookingRequest) {

        // check if customer exists or not...
        Optional<Customer> customer = customerRepository.findById(bookingRequest.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("customer not found");
        }

        InventoryResponse event = inventoryServiceClient.getInventory(bookingRequest.getEventId());
        if (event.getCapacity() < bookingRequest.getTicketCount()) {
            throw new RuntimeException("no space left in event");
        }

        BookingResponse booking = createBooking(bookingRequest, customer.get(), event);

        return booking;
    }

    static BookingResponse createBooking(BookingRequest bookingRequest, final Customer customer,
            InventoryResponse inventoryResponse) {
        return BookingResponse.builder().customerId(customer.getId()).ticketCount(bookingRequest.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice())
                .eventId(inventoryResponse.getEventId())
                .build();

    }

}
