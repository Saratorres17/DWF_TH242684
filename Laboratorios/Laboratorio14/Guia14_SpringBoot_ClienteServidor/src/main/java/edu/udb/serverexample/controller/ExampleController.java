package edu.udb.serverexample.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ExampleController {

    @GetMapping("/example")
    public Map<String, Object> example() {
        return Map.of(
            "message", "Hola desde Spring Boot 😄",
            "status", "OK",
            "timestamp", System.currentTimeMillis()
        );
    }
}
