package com.example.road_control_system.services;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.road_control_system.dto.TicketResponseDTO;
import com.example.road_control_system.exceptions.CustomNotFoundException;
import com.example.road_control_system.models.Route;
import com.example.road_control_system.models.Ticket;
import com.example.road_control_system.models.Vehicle;
import com.example.road_control_system.repositories.RouteRepository;
import com.example.road_control_system.repositories.TicketRepository;
import com.example.road_control_system.repositories.VehicleRepository;


@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;
    private final RouteRepository routeRepository;
    public TicketService(TicketRepository ticketRepository, VehicleRepository vehicleRepository, RouteRepository routeRepository) {
            this.ticketRepository = ticketRepository;
            this.vehicleRepository = vehicleRepository;
            this.routeRepository = routeRepository;
        }


    public List<Ticket> getAllTickets() {
            return ticketRepository.findAll();
        }

    public TicketResponseDTO getTicketById(Long id) {
            return new TicketResponseDTO(ticketRepository.findById(id).orElseThrow(
                () -> new CustomNotFoundException("Ticket not found", "/tickets/" + id)
            ));
        }

    public TicketResponseDTO createTicket(Long vehicleId, Long routeId) {
        if (vehicleId == null || routeId == null) {
            throw new IllegalArgumentException("Vehicle ID and Route ID must not be null");
        }

        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        Optional<Route> route = routeRepository.findById(routeId);

        if(!vehicle.isPresent()) {
            throw new CustomNotFoundException("Vehicle not found", "/vehicles/" + vehicleId);
        }
        if(!route.isPresent()) {
            throw new CustomNotFoundException("Route not found", "/routes/" + routeId);
            }
        

        if (vehicle.isPresent() && route.isPresent()) {
            Vehicle vehicleEntity = vehicle.get();
            Route routeEntity = route.get();

            boolean isSpeeding = vehicleEntity.getSpeed() > routeEntity.getSpeedMax();
            boolean isOverweight = vehicleEntity.getWeight() > routeEntity.getWeightMax();

            if (isSpeeding || isOverweight) {
                Ticket ticket = new Ticket();

                if (isSpeeding && isOverweight) {
                    ticket.setType("Speeding/Overweight");
                    ticket.setFine(200);
                    ticket.setDescription("Speeding by " + (vehicleEntity.getSpeed() - routeEntity.getSpeedMax()) 
                            + " km/h and overweight by " + (vehicleEntity.getWeight() - routeEntity.getWeightMax()) + " kg");
                } else if (isSpeeding) {
                    ticket.setType("Speeding");
                    ticket.setFine(100);
                    ticket.setDescription("Speeding by " + (vehicleEntity.getSpeed() - routeEntity.getSpeedMax()) + " km/h");
                } else {
                    ticket.setType("Overweight");
                    ticket.setFine(100);
                    ticket.setDescription("Overweight by " + (vehicleEntity.getWeight() - routeEntity.getWeightMax()) + " kg");
                }

                ticket.setStatus("Pending");
                ticket.setVehicle(vehicleEntity);
                ticket.setRoute(routeEntity);

                Ticket savedTicket = ticketRepository.save(ticket);
                return new TicketResponseDTO(savedTicket);
            } else {
                throw new IllegalArgumentException("Vehicle is neither speeding nor overweight");
            }
        } else {    
            throw new IllegalArgumentException("Vehicle or Route not found");
        }
        

    }

    public Ticket updateTicket(Long id, Ticket updatedTicket) {
            Ticket existingTicket = ticketRepository.findById(id).orElse(null);

            if (existingTicket != null) {
                existingTicket.setType(updatedTicket.getType());
                existingTicket.setDescription(updatedTicket.getDescription());
                existingTicket.setStatus(updatedTicket.getStatus());
                existingTicket.setFine(updatedTicket.getFine());
                return ticketRepository.save(existingTicket);
            } else {
                throw new IllegalArgumentException("Ticket not found");
            }
        }

    public List<Ticket> getTicketsByVehicleId(Long vehicleId) {
            return ticketRepository.findByVehicleId(vehicleId);
        }

    public List<Ticket> getTicketsByRouteId(Long routeId) {
            return ticketRepository.findByRouteId(routeId);
        }

    public void deleteTicket(Long id) {
                ticketRepository.deleteById(id);
            }
        
    public void deleteAllTickets() {
                ticketRepository.deleteAll();
            }



    }
