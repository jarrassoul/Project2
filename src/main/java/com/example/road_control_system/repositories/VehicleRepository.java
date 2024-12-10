package com.example.road_control_system.repositories;

import com.example.road_control_system.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository  extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE TYPE(v) = :type")
    List<Vehicle> findByType(@Param("type") Class<? extends Vehicle> type);
}