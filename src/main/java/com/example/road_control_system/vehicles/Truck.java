package com.example.road_control_system.vehicles;

import com.example.road_control_system.models.Vehicle;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Truck extends Vehicle {
    private String loadTruck;

}