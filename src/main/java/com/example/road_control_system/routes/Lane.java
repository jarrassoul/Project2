package com.example.road_control_system.routes;

import com.example.road_control_system.models.Route;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Lane extends Route {
    private int laneCount;
}