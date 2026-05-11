package com.ticketflow.apigateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;

import java.net.URI;

@Configuration
public class BookingServiceRoutes {

        @Value("${booking.service.url}")
        String bookingServiceUrl;

        @Bean
        public RouterFunction<ServerResponse> bookingRoutes() {
                return GatewayRouterFunctions.route("booking-service")
                                .route(RequestPredicates.POST("/api/v1/booking"), HandlerFunctions.http())
                                .before(uri(URI.create(bookingServiceUrl)))
                                .before(setPath("/booking/addBooking"))
                                .build();
        }
}
