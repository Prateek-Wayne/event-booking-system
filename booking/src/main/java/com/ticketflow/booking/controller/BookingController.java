package com.ticketflow.booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketflow.booking.dto.request.BookingRequest;
import com.ticketflow.booking.dto.response.BookingResponse;
import com.ticketflow.booking.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/booking")
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(path = "/addBooking", consumes = "application/json", produces = "application/json")
    public BookingResponse postMethodName(@RequestBody BookingRequest bookingRequest) {
        try {
            log.info("POST /booking/addBooking customerId={} eventId={} ticketCount={}",
                    bookingRequest.getCustomerId(), bookingRequest.getEventId(), bookingRequest.getTicketCount());
            BookingResponse bookingResponse = bookingService.createBooking(bookingRequest);
            log.info("POST /booking/addBooking success customerId={} eventId={}",
                    bookingResponse.getCustomerId(), bookingResponse.getEventId());
            return bookingResponse;
        } catch (Exception exception) {
            log.error("POST /booking/addBooking failed customerId={} eventId={} ticketCount={}",
                    bookingRequest.getCustomerId(), bookingRequest.getEventId(), bookingRequest.getTicketCount(),
                    exception);
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

}
