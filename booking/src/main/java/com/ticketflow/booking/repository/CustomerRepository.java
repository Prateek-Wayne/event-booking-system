package com.ticketflow.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketflow.booking.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
