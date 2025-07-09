package dwf.semana2.saratorres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Para devolver códigos de estado HTTP
import org.springframework.http.ResponseEntity; // Para construir respuestas HTTP
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * relacionadas con los datos.
 */
@RestController
@RequestMapping("/api/datos")
public class MiControlador {

    @Autowired
    private MiServicio miServicio;

    /**
     * Maneja las solicitudes HTTP GET a la ruta /api/datos.
     * Devuelve una lista de cadenas (String).
     * @return Una lista de datos.
     */
    @GetMapping
    public List<String> obtenerDatos() {
        return miServicio.obtenerDatos();
    }

    /**
     * Maneja las solicitudes HTTP POST a la ruta /api/datos.
     * Recibe un String en el cuerpo de la solicitud (RequestBody) y lo agrega a la lista.
     * @param nuevoDato El dato a agregar, enviado en el cuerpo de la solicitud.
     * @return Un mensaje de confirmación con el dato agregado.
     */
    @PostMapping
    public String agregarDato(@RequestBody String nuevoDato) {
        miServicio.agregarDato(nuevoDato);
        return "Dato agregado correctamente: " + nuevoDato;
    }

    /**
     * Maneja las solicitudes HTTP PUT a la ruta /api/datos/{index}.
     * Actualiza un dato existente en la lista.
     * @param index El índice del dato a actualizar, tomado de la URL.
     * @param nuevoValor El nuevo valor para el dato, enviado en el cuerpo de la solicitud.
     * @return ResponseEntity con un mensaje de éxito o error y el estado HTTP.
     */
    @PutMapping("/{index}") // Mapea solicitudes PUT con un índice en la URL
    public ResponseEntity<String> actualizarDato(@PathVariable int index, @RequestBody String nuevoValor) {
        if (miServicio.actualizarDato(index, nuevoValor)) {
            return new ResponseEntity<>("Dato actualizado correctamente en el índice " + index, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Índice " + index + " no válido para actualizar.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Maneja las solicitudes HTTP DELETE a la ruta /api/datos/{index}.
     * Elimina un dato de la lista por su índice.
     * @param index El índice del dato a eliminar, tomado de la URL.
     * @return ResponseEntity con un mensaje de éxito o error y el estado HTTP.
     */
    @DeleteMapping("/{index}") // Mapea solicitudes DELETE con un índice en la URL
    public ResponseEntity<String> eliminarDato(@PathVariable int index) {
        if (miServicio.eliminarDato(index)) {
            return new ResponseEntity<>("Dato eliminado correctamente del índice " + index, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Índice " + index + " no válido para eliminar.", HttpStatus.NOT_FOUND);
        }
    }
}
