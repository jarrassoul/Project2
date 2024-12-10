package com.example.road_control_system.routes;
import com.example.road_control_system.models.Route;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Bridge extends Route {
    private double loadCapacity;
    
}
