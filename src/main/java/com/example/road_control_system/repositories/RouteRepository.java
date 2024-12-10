package com.example.road_control_system.repositories;

import com.example.road_control_system.models.Route;
import com.example.road_control_system.routes.Lane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;  

public interface RouteRepository extends JpaRepository<Route, Long> {
    // List<Route> findByType(String type);
    @Query("SELECT r FROM Lane r WHERE r.laneCount = :laneCount")
    List<Lane> findLanesByLaneCount(@Param("laneCount") int laneCount);

//    @Query("SELECT l FROM Lane l WHERE l.id = :id")
//    Lane findLaneById(@Param("id") Long id);

    @Query("SELECT r FROM Route r WHERE TYPE(r) = :type")
    List<Route> findByType(@Param("type") Class<? extends Route> type);



}
