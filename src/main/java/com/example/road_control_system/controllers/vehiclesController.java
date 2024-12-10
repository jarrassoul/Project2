package com.example.road_control_system.controllers;
import com.example.road_control_system.models.Vehicle;
import com.example.road_control_system.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")

public class vehiclesController {
    private final VehicleService vehicleService;

    public vehiclesController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<String> createVehicle(@RequestBody Vehicle vehicle) {
        System.out.println("Vehicle is " +vehicle);
        vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>("Vehicle created", HttpStatus.CREATED);
    }
    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>("Vehicle deleted", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllVehicles() {
        vehicleService.deleteAllVehicles();
        return new ResponseEntity<>("All Vehicles deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        if (updatedVehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public List<Vehicle> getVehcicleByType(@PathVariable String type){
        return vehicleService.getVehicleByType(type);
    }

    @PostMapping("/bulk")
    public List<Vehicle> createVehicles(@RequestBody List<Vehicle> vehicles) {
        return vehicleService.createVehicles(vehicles);
    }
}