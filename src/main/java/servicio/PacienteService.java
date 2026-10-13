
package servicio;

// Importa la clase que representa la información de un paciente.
import modelo.Paciente;

// Permite conectar automáticamente el repositorio con el servicio.
import org.springframework.beans.factory.annotation.Autowired;

// Indica a Spring que esta clase contiene la lógica del servicio.
import org.springframework.stereotype.Service;

// Importa el repositorio que permite acceder a los datos de los pacientes.
import repositorio.PacienteRepository;

// Permite trabajar con listas de pacientes.
import java.util.List;

/**
 * Servicio encargado de gestionar las operaciones de los pacientes
 * del sistema Soulence.
 * Se comunica con el repositorio para consultar y modificar los datos.
 */
@Service
public class PacienteService {

    // Conecta automáticamente el repositorio de pacientes.
    @Autowired
    private PacienteRepository pacienteRepository;

    // Registra un paciente y devuelve la información guardada.
    public Paciente guardar(Paciente paciente) {
        // Utiliza el repositorio para guardar los datos del paciente.
        return pacienteRepository.save(paciente);
    }

    // Consulta y devuelve todos los pacientes registrados.
    public List<Paciente> listar() {
        // Busca todos los registros almacenados en la base de datos.
        return pacienteRepository.findAll();
    }

    // Busca un paciente mediante su identificador.
    public Paciente buscarPorId(int id) {
        // Si encuentra el paciente, devuelve sus datos.
        // Si no existe, devuelve null.
        return pacienteRepository.findById(id).orElse(null);
    }

    // Actualiza la información de un paciente.
    public Paciente actualizar(Paciente paciente) {
        // Guarda los cambios utilizando el identificador del paciente.
        return pacienteRepository.save(paciente);
    }

    // Elimina un paciente utilizando su identificador.
    public void eliminar(int id) {
        // Solicita al repositorio eliminar el registro indicado.
        pacienteRepository.deleteById(id);
    }
}
