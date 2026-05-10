package com.ticketflow.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class BookingServiceRoutes {

        @Bean
        public RouterFunction<ServerResponse> bookingRoutes() {
                return GatewayRouterFunctions.route("booking-service")
                                .route(RequestPredicates.POST("/api/v1/booking"),
                                                HandlerFunctions.http("http://localhost:8081/booking/addBooking"))
                                .build();
        }
}
