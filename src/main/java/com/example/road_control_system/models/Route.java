package com.example.road_control_system.models;


import java.util.List;

import com.example.road_control_system.routes.Avenue;
import com.example.road_control_system.routes.Bridge;
import com.example.road_control_system.routes.Highway;
import com.example.road_control_system.routes.Lane;
import com.example.road_control_system.routes.Road;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routes")


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Lane.class, name = "lane"),
        @JsonSubTypes.Type(value = Road.class, name = "road"),
        @JsonSubTypes.Type(value = Avenue.class, name = "avenue"),
        @JsonSubTypes.Type(value = Bridge.class, name = "bridge"),
        @JsonSubTypes.Type(value = Highway.class, name = "highway")
})
@Inheritance(strategy = InheritanceType.JOINED) // You can change to SINGLE_TABLE or TABLE_PER_CLASS
@DiscriminatorColumn(name = "route_type", discriminatorType = DiscriminatorType.STRING)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double length;

    @Column(nullable = false)
    private String name;

    private Double speedMax;

    private Double weightMax;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonManagedReference
    private List<Ticket> tickets;

    

}
