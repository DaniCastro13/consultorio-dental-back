package com.consultorio.dental_s.Controllers;

import com.consultorio.dental_s.Models.LoginRequest;
import com.consultorio.dental_s.ServiceImpl.JwtUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            log.info("LOGIN: Iniciando el controlador del login");
            log.info("REQUEST LOGIN: username {} : password {} ", loginRequest.getUsername(), loginRequest.getPassword());
            Map<String, Object> map = new HashMap<>();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()
            ));
            UserDetails userService = userDetailsService.loadUserByUsername(loginRequest.getUsername());

            //GENERAR TOKEN
            String jwt = jwtUtilService.generateToken(userService);
            map.put("token", jwt);
            map.put("user", userService.getUsername());
            map.put("roles", userService.getAuthorities());
            log.info("LOGIN: Token Generado {}", jwt);
            log.info("LOGIN: el controlador del login exitoso");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception e) {
            log.error("LOGIN: Error al consumir el servicio de autenticacion");
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
