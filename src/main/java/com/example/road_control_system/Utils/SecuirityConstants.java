package com.example.road_control_system.Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.Data;


@Data
public class SecuirityConstants {
    public static final SecretKey Jwt_SECRET = new SecretKeySpec("A_Secure_256_bit_key_Example!".getBytes(), "HmacSHA256");
    public static final long Expiration_DATE = 3600000; // 1 hour
    
}
