package com.example.road_control_system.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.road_control_system.exceptions.CustomNotFoundException;
import com.example.road_control_system.models.Vehicle;
import com.example.road_control_system.repositories.VehicleRepository;
import com.example.road_control_system.vehicles.Bus;
import com.example.road_control_system.vehicles.Car;
import com.example.road_control_system.vehicles.SemiTruck;
import com.example.road_control_system.vehicles.Truck;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles() {

        return this.vehicleRepository.findAll();
    }
    //Function return Vehicle by ID
    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id).orElseThrow(
             () -> new CustomNotFoundException("Vehicle not found", "/vehicles/" + id)
        );
    }

    //Create Vehicle
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    //Function that update Vehicle
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle existingVehicle = getVehicleById(id);
        if (existingVehicle != null) {
            existingVehicle.setLicenseplate(vehicle.getLicenseplate());
            existingVehicle.setSpeed(vehicle.getSpeed());
            existingVehicle.setWeight(vehicle.getWeight());
            return vehicleRepository.save(existingVehicle);
        }
        return null;
    }

   //Delete a Vehicle by ID
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id); }

    //Delete all the Vehicles
    public void deleteAllVehicles() {
        vehicleRepository.deleteAll(); }

    public List<Vehicle> getVehicleByType(String type) {
        Class<? extends Vehicle> vehicleClass = mapTypeToClass(type);
    return vehicleRepository.findByType(vehicleClass);
    }
    private Class<? extends Vehicle> mapTypeToClass(String type) {
        switch (type.toLowerCase()) {
            case "bus" -> {
                return Bus.class;
            }
            case "car" -> {
                return Car.class;
            }
            case "truck" -> {
                return Truck.class;
            }
            case "semitruck" -> {
                return SemiTruck.class;
            }
            default -> throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }

    public List<Vehicle> createVehicles(List<Vehicle> vehicles) {
        return vehicleRepository.saveAll(vehicles);
    }
}