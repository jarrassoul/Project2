package com.example.road_control_system.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.road_control_system.models.Route;
import com.example.road_control_system.services.RouteService;


@RestController
@RequestMapping("/api/v1/routes")
public class routesController {

    @Autowired
    private RouteService routeService;

   @PostMapping
   public ResponseEntity<String> createRoute(@RequestBody Route route) {

        System.out.println( "route is " + route);
        routeService.addRoute(route);
         return new ResponseEntity<>("Route created", HttpStatus.CREATED);

   }

   @GetMapping
    public List<Route> getRoutes() {
         return routeService.getRoutes();
    }

    // Get a route by id
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable final Long id) {
        Route route = routeService.getRouteById(id);
        if (route == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

      @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        Route updatedRoute = routeService.updateRoute(id, route);
        if (updatedRoute == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable final Long id) {
        routeService.deleteRoute(id);
        return new ResponseEntity<>("Route deleted", HttpStatus.OK);
        }


    @DeleteMapping
    public ResponseEntity<String> deleteAllRoutes() {
        routeService.deleteAllRoutes();
        return new ResponseEntity<>("All routes deleted", HttpStatus.OK);
        }

    @GetMapping("/type/{type}")
    public List<Route> getRoutesByType(@PathVariable String type) {
        return routeService.getRoutesByType(type);
    }

    @PostMapping("/bulk")
    public List<Route> createRoutes(@RequestBody List<Route> routes) {
        //TODO: process POST request
        
        return routeService.createRoutes(routes);
    }
    
}