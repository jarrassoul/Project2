package com.example.road_control_system.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.road_control_system.exceptions.CustomNotFoundException;
import com.example.road_control_system.models.Route;
import com.example.road_control_system.repositories.RouteRepository;
import com.example.road_control_system.routes.Avenue;
import com.example.road_control_system.routes.Bridge;
import com.example.road_control_system.routes.Highway;
import com.example.road_control_system.routes.Lane;
import com.example.road_control_system.routes.Road;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RouteService {
   
    private final RouteRepository routeRepository;


    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }


    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElseThrow(
             () -> new CustomNotFoundException("Route not found", "/routes/" + id)
        );
    }

    public Route addRoute(Route route) {

            if (route.getName() == null || route.getName().isEmpty()) {
        throw new IllegalArgumentException("Route name must not be null or empty.");
    }
        return routeRepository.save(route);
    }

    public Route updateRoute( Long id, Route route) {
        Route existingRoute = getRouteById(id);
        if (existingRoute != null) {
            existingRoute.setName(route.getName());
            existingRoute.setSpeedMax(route.getSpeedMax());
            existingRoute.setLength(route.getLength());
            // existingRoute.setRouteType(route.getRouteType());
            existingRoute.setWeightMax(route.getWeightMax());
            return routeRepository.save(existingRoute);
            }
        return null;
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    public void deleteAllRoutes() {
        routeRepository.deleteAll();
    }

public List<Lane> getLanesByLaneCount(int laneCount) {
    return routeRepository.findLanesByLaneCount(laneCount);
}

    // public Lane getLaneById(Long id) {
    //         Lane lane = routeRepository.findLaneById(id);
    //         if (lane == null) {
    //             throw new RuntimeException("Lane not found with ID: " + id);
    //         }
    //         return lane;
    //     }

    // public Lane updateLane(Long id, Lane lane) {
    //     Lane existingLane = getLaneById(id);
    //     if (existingLane != null) {
    //         existingLane.setLaneCount(lane.getLaneCount());
    //         existingLane.setLength(lane.getLength());
    //         existingLane.setName(lane.getName());
    //         existingLane.setSpeedMax(lane.getSpeedMax());

    //         return routeRepository.save(existingLane);
    //     }
    //     return null;
    // }

    // public void deleteLane(Long id) {
    //         routeRepository.deleteById(id);
    //         }

    // public void deleteAllLanes() {
    //         routeRepository.deleteAll();
    //         }

    public List<Route> getRoutesByType(String type) {
            Class<? extends Route> routeClass = mapTypeToClass(type);
            return routeRepository.findByType(routeClass);
        }

    private Class<? extends Route> mapTypeToClass(String type) {
            switch (type.toLowerCase()) {
                case "lane" -> {
                    return Lane.class;
            }
                case "road" -> {
                    return Road.class;
            }
                case "avenue" -> {
                    return Avenue.class;
            }
                case "bridge" -> {
                    return Bridge.class;
            }
                case "highway" -> {
                    return Highway.class;
            }
                default -> throw new IllegalArgumentException("Invalid route type: " + type);
            }
        }


        public List<Route> createRoutes(List<Route> routes) {
            return routeRepository.saveAll(routes);
        }

}