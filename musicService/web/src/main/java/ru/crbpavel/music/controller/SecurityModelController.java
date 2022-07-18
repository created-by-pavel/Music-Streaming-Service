package ru.crbpavel.music.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.crbpavel.music.model.SecurityModel;
import ru.crbpavel.music.service.SecurityModelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "security")
public class SecurityModelController {
    @Autowired
    SecurityModelService service;

    @PostMapping(path = "add")
    public void addUser(@RequestBody SecurityModel securityModel) {
        service.save(securityModel);
    }

    @GetMapping(path = "all")
    public List<SecurityModel> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "get-by-id/{id}")
    public SecurityModel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping(path = "update")
    public void updateUser(@RequestBody SecurityModel securityModel) {
        service.updateUser(securityModel);
    }

    @GetMapping(path = "token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshedToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshedToken);
                String username = decodedJWT.getSubject();
                SecurityModel user = service.findByName(username);
                String accessToken = JWT.create()
                        .withSubject(user.getName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRole().toString())
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshedToken", refreshedToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
