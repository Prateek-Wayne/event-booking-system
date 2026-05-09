package com.ticketflow.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketflow.inventory.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    
}
