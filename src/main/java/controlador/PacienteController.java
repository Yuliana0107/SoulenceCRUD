
package controlador;

// Importa el modelo que contiene los datos de un paciente.
import modelo.Paciente;

// Permite utilizar la anotación para conectar el servicio automáticamente.
import org.springframework.beans.factory.annotation.Autowired;

// Importa las anotaciones necesarias para crear las rutas de la API REST.
import org.springframework.web.bind.annotation.*;

// Importa el servicio que contiene las operaciones de los pacientes.
import servicio.PacienteService;

// Permite manejar listas de pacientes.
import java.util.List;

/**
 * Controlador REST de Soulence.
 * Recibe las solicitudes relacionadas con los pacientes
 * y las envía al servicio para realizar las operaciones.
 */
@RestController

// Define la ruta principal para las operaciones de pacientes.
@RequestMapping("/pacientes")
public class PacienteController {

    // Conecta automáticamente el servicio de pacientes con el controlador.
    @Autowired
    private PacienteService pacienteService;

    // Consulta y devuelve la lista de todos los pacientes registrados.
    @GetMapping
    public List<Paciente> listar() {
        // Solicita al servicio la lista de pacientes.
        return pacienteService.listar();
    }

    // Busca un paciente específico utilizando su identificador.
    @GetMapping("/{id}")
    public Paciente buscar(@PathVariable int id) {
        // Recibe el ID de la URL y lo envía al servicio.
        return pacienteService.buscarPorId(id);
    }

    // Recibe los datos necesarios para registrar un paciente.
    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente) {
        // Envía los datos del paciente al servicio para guardarlos.
        return pacienteService.guardar(paciente);
    }

    // Recibe los datos modificados de un paciente existente.
    @PutMapping
    public Paciente actualizar(@RequestBody Paciente paciente) {
        // Solicita al servicio que actualice la información del paciente.
        return pacienteService.actualizar(paciente);
    }

    // Define la ruta para eliminar un paciente por su ID.
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        // Envía el identificador al servicio para realizar la eliminación.
        pacienteService.eliminar(id);
    }
}
