package com.example.road_control_system.vehicles;

import com.example.road_control_system.models.Vehicle;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Car extends Vehicle {
    //private  Long carCount;
}