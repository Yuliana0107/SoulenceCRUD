
package repositorio;

// Importa la clase que representa los datos de un paciente.
import modelo.Paciente;

// Permite utilizar las operaciones de acceso a datos de Spring Data JPA.
import org.springframework.data.jpa.repository.JpaRepository;

// Permite identificar esta interfaz como un repositorio de Spring.
import org.springframework.stereotype.Repository;

/**
 * Repositorio encargado de acceder a los datos de los pacientes.
 * Hereda de JpaRepository las operaciones CRUD necesarias
 * para registrar, consultar, actualizar y eliminar pacientes.
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    // No es necesario escribir los métodos CRUD manualmente,
    // porque JpaRepository ya proporciona estas operaciones.
}
