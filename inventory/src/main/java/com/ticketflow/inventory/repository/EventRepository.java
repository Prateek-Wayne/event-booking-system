package com.ticketflow.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketflow.inventory.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
