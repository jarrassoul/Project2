package com.example.road_control_system.routes;

import jakarta.persistence.Entity;

import com.example.road_control_system.models.Route;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Road extends Route {
    private boolean isPaved;
}
