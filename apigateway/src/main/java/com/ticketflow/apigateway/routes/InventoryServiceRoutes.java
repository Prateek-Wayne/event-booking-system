package com.ticketflow.apigateway.routes;

import java.net.URI;

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

@Configuration
public class InventoryServiceRoutes {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> addVenueRoute() {
        return GatewayRouterFunctions.route("inventory-add-venue")
                .route(RequestPredicates.POST("/api/v1/inventory/addVenue"), HandlerFunctions.http())
                .before(uri(URI.create(inventoryServiceUrl)))
                .before(setPath("/inventory/addVenue"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> addEventRoute() {
        return GatewayRouterFunctions.route("inventory-add-event")
                .route(RequestPredicates.POST("/api/v1/inventory/addEvent"), HandlerFunctions.http())
                .before(uri(URI.create(inventoryServiceUrl)))
                .before(setPath("/inventory/addEvent"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> getEventsRoute() {
        return GatewayRouterFunctions.route("inventory-events")
                .route(RequestPredicates.GET("/api/v1/inventory/events"), HandlerFunctions.http())
                .before(uri(URI.create(inventoryServiceUrl)))
                .before(setPath("/inventory/events"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> getEventByIdRoute() {
        return GatewayRouterFunctions.route("inventory-event-by-id")
                .route(RequestPredicates.GET("/api/v1/inventory/event/{eventId}"), HandlerFunctions.http())
                .before(uri(URI.create(inventoryServiceUrl)))
                .before(setPath("/inventory/event/{eventId}"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> updateEventCapacityRoute() {
        return GatewayRouterFunctions.route("inventory-event-capacity")
                .route(RequestPredicates.PUT("/api/v1/inventory/event/{eventId}/capacity/{capacity}"),
                        HandlerFunctions.http())
                .before(uri(URI.create(inventoryServiceUrl)))
                .before(setPath("/inventory/event/{eventId}/capacity/{capacity}"))
                .build();
    }
}