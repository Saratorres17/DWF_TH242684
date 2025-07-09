package dwf.semana2.saratorres;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class MiServicio {

    private final List<String> datos = new ArrayList<>();

    public MiServicio() {
        // Datos iniciales
        datos.add("Elemento 1");
        datos.add("Elemento 2");
    }

    /**
     *  lista completa de datos.
     * @return Una lista de cadenas.
     */
    public List<String> obtenerDatos() {
        return datos;
    }

    /**
     * Agrega un nuevo dato a la lista.
     * @param nuevoDato El dato a agregar.
     */
    public void agregarDato(String nuevoDato) {
        datos.add(nuevoDato);
    }

    /**
     * Actualiza un dato existente en la lista.
     * @param index El índice del dato a actualizar.
     * @param nuevoValor El nuevo valor para el dato.
     * @return true si el dato fue actualizado, false si el índice es inválido.
     */
    public boolean actualizarDato(int index, String nuevoValor) {
        if (index >= 0 && index < datos.size()) {
            datos.set(index, nuevoValor);
            return true;
        }
        return false; // Índice fuera de rango
    }

    /**
     * Elimina un dato de la lista por su índice.
     * @param index El índice del dato a eliminar.
     * @return true si el dato fue eliminado, false si el índice es inválido.
     */
    public boolean eliminarDato(int index) {
        if (index >= 0 && index < datos.size()) {
            datos.remove(index);
            return true;
        }
        return false; // Índice fuera de rango
    }
}