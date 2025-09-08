package sv.edu.udb.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.request.ClientRequest;
import static org.springframework.http.HttpStatus.CREATED;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "clients")
public class ClientController {
    @PostMapping
    @ResponseStatus(CREATED)
    public String checkPhoneNumber(@Valid @RequestBody final ClientRequest request) {
        return "Número de teléfono válido: " + request.getPhoneNumber();
    }
}