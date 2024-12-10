package com.example.road_control_system.controllers.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.road_control_system.Utils.EncryptPassword.encryptPassword;
import com.example.road_control_system.Utils.JwtUtil;
import com.example.road_control_system.models.User;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    // @Autowired
    // private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint for user authentication and JWT generation
    // @PostMapping("/authenticate")
    // public String createJWT(@RequestBody User user) {
    //     String username =  user.getUsername();
    //      String password = user.getPassword();
    //      String passwordEncreypted = encryptPassword(password);
        // Authentication authentication = authenticationManager.authenticate(
        //         new UsernamePasswordAuthenticationToken(username, passwordEncreypted)
        // );

    //     return jwtUtil.generateToken(username);
    // }



}
