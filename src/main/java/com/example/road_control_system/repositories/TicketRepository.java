package com.example.road_control_system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.road_control_system.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByVehicleId(Long vehicleId);

    List<Ticket> findByRouteId(Long routeId);
    
}
