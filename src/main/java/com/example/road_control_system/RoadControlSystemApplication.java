package com.example.road_control_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
@SpringBootApplication
public class RoadControlSystemApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();

        // Set system properties for Spring to access
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        // System.setProperty("API_KEY", dotenv.get("API_KEY"));
        SpringApplication.run(RoadControlSystemApplication.class, args);
    }

}
