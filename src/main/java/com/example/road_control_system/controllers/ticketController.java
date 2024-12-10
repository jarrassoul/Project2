package com.example.road_control_system.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.road_control_system.dto.TicketRequest;
import com.example.road_control_system.dto.TicketResponseDTO;
import com.example.road_control_system.models.Ticket;
import com.example.road_control_system.services.TicketService;


@RestController
@RequestMapping("/api/v1/tickets")
public class ticketController {
        private final TicketService  ticketService;

        
        public ticketController(TicketService ticketService) {
            this.ticketService = ticketService;
        }

        @GetMapping
        public List<Ticket> getTickets() {
            return ticketService.getAllTickets();
        }

        @GetMapping("/{id}")
        public TicketResponseDTO getTicket(@PathVariable Long id) {
            return ticketService.getTicketById(id);
            }

   @PostMapping
public ResponseEntity<?> createTicket(@RequestBody TicketRequest ticketRequest) {
    try {
        // Create the ticket and wrap it in a TicketResponseDTO
        TicketResponseDTO ticketResponse = ticketService.createTicket(
            ticketRequest.getVehicleId(),
            ticketRequest.getRouteId()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(200).body(
            Map.of("error", "Vehicle is not speeding nor overweighting", "message", e.getMessage())
        );
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            Map.of("error", "An unexpected error occurred", "message", e.getMessage())
        );
    }
}

        @PutMapping("/{id}")
        public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
            return ticketService.updateTicket(id, ticket);
            
        }

        @GetMapping("/vehicle/{vehicleId}")
        public List<Ticket> getTicketsByVehicle(@PathVariable Long vehicleId) {
            return ticketService.getTicketsByVehicleId(vehicleId);
            }
        
        @GetMapping("/route/{routeId}")
        public List<Ticket> getTicketsByRoute(@PathVariable Long routeId) {
            return ticketService.getTicketsByRouteId(routeId);
            }

        @DeleteMapping("/{id}")
        public void  deleteTicket(@PathVariable Long id) {
             ticketService.deleteTicket(id);
            }
        
        @DeleteMapping
        public void deleteAllTickets() {
             ticketService.deleteAllTickets();
            }

}
