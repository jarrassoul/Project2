package com.example.road_control_system.models;
import java.util.List;

import com.example.road_control_system.vehicles.Bus;
import com.example.road_control_system.vehicles.Car;
import com.example.road_control_system.vehicles.SemiTruck;
import com.example.road_control_system.vehicles.Truck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bus.class, name = "bus"),
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = SemiTruck.class, name = "semi_truck"),
        @JsonSubTypes.Type(value = Truck.class, name = "truck")
})
@Inheritance(strategy = InheritanceType.JOINED) // You can change to SINGLE_TABLE or TABLE_PER_CLASS
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String licenseplate;
 private Double weight;
 private String model;
 private double speed;


 @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
  @JsonIgnore
  @JsonManagedReference
 private List<Ticket> tickets;


}

