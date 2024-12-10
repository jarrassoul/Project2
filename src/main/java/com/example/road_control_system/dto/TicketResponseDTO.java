package com.example.road_control_system.dto;

import com.example.road_control_system.models.Ticket;
import com.example.road_control_system.models.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.road_control_system.models.Route;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {
    private Long id;
    private String type;
    private String description;
    private String status;
    private double fine;
    private Vehicle vehicle;
    private Route route;

    public TicketResponseDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.type = ticket.getType();
        this.description = ticket.getDescription();
        this.status = ticket.getStatus();
        this.fine = ticket.getFine();
        this.vehicle = ticket.getVehicle();
        this.route = ticket.getRoute();
    }


}
