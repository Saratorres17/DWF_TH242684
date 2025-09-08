package sv.edu.udb.controller.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import sv.edu.udb.controller.validation.PhoneNumber;
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientRequest {
    @NotBlank
    @PhoneNumber(message = "Número de teléfono inválido", pattern = "^\\d{4}-\\d{4}$")
    private String phoneNumber;
}