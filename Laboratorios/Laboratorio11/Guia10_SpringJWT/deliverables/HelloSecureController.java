package sv.edu.udb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class HelloSecureController {
    @GetMapping("/hello")
    public String hello() {
        return "Hola, este es un recurso protegido con JWT";
    }
}