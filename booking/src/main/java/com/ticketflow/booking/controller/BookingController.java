package com.ticketflow.booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketflow.booking.dto.request.BookingRequest;
import com.ticketflow.booking.dto.response.BookingResponse;
import com.ticketflow.booking.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(path = "/addBooking", consumes = "application/json", produces = "application/json")
    public BookingResponse postMethodName(@RequestBody BookingRequest bookingRequest) {
        return bookingService.createBooking(bookingRequest);

    }

}
