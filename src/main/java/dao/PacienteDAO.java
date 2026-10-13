
package dao;

// Importa la clase encargada de establecer la conexión con la base de datos.
import conexion.Conexion;

// Importa el modelo que representa la información de un paciente.
import modelo.Paciente;

// Importa las clases necesarias para ejecutar consultas SQL.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importa las clases para almacenar y devolver listas de pacientes.
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de realizar las operaciones de acceso a datos
 * para gestionar los pacientes de Soulence.
 */
public class PacienteDAO {

    /**
     * Registra un nuevo paciente en la base de datos.
     * Recibe un objeto Paciente con la información que se guardará.
     */
    public boolean registrarPaciente(Paciente paciente) {

        // Consulta SQL para insertar los datos de un paciente.
        String sql = "INSERT INTO pacientes " +
                "(nombre, apellido, documento, telefono, correo) " +
                "VALUES (?, ?, ?, ?, ?)";

        // Abre la conexión y prepara la consulta SQL.
        // Los recursos se cierran automáticamente al terminar.
        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            // Asigna los valores del paciente a los parámetros de la consulta.
            sentencia.setString(1, paciente.getNombre());
            sentencia.setString(2, paciente.getApellido());
            sentencia.setString(3, paciente.getDocumento());
            sentencia.setString(4, paciente.getTelefono());
            sentencia.setString(5, paciente.getCorreo());

            // Ejecuta la consulta para guardar el paciente.
            sentencia.executeUpdate();

            // Indica que la operación terminó sin generar una excepción.
            return true;

        } catch (SQLException e) {

            // Muestra el mensaje de error si falla el registro.
            System.out.println("Error al registrar paciente: "
                    + e.getMessage());

            return false;
        }
    }

    /**
     * Consulta los pacientes almacenados en la base de datos.
     * Devuelve una lista con la información encontrada.
     */
    public List<Paciente> consultarPacientes() {

        // Crea una lista vacía para almacenar los pacientes consultados.
        List<Paciente> pacientes = new ArrayList<>();

        // Consulta SQL para obtener todos los registros de pacientes.
        String sql = "SELECT * FROM pacientes";

        // Ejecuta la consulta y obtiene los resultados.
        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            // Recorre los resultados uno por uno.
            while (resultado.next()) {

                // Crea un objeto para representar al paciente encontrado.
                Paciente paciente = new Paciente();

                // Copia los datos de la fila consultada al objeto Paciente.
                paciente.setId(resultado.getInt("id"));
                paciente.setNombre(resultado.getString("nombre"));
                paciente.setApellido(resultado.getString("apellido"));
                paciente.setDocumento(resultado.getString("documento"));
                paciente.setTelefono(resultado.getString("telefono"));
                paciente.setCorreo(resultado.getString("correo"));

                // Agrega el paciente a la lista.
                pacientes.add(paciente);
            }

        } catch (SQLException e) {

            // Muestra el error si no es posible consultar los pacientes.
            System.out.println("Error al consultar pacientes: "
                    + e.getMessage());
        }

        // Devuelve la lista con los pacientes encontrados.
        return pacientes;
    }

    /**
     * Actualiza la información de un paciente existente.
     * Utiliza su identificador para saber qué registro modificar.
     */
    public boolean actualizarPaciente(Paciente paciente) {

        // Consulta SQL para modificar los datos del paciente seleccionado.
        String sql = "UPDATE pacientes SET " +
                "nombre = ?, apellido = ?, documento = ?, " +
                "telefono = ?, correo = ? WHERE id = ?";

        // Abre la conexión y prepara la consulta de actualización.
        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            // Asigna los nuevos datos a los parámetros de la consulta.
            sentencia.setString(1, paciente.getNombre());
            sentencia.setString(2, paciente.getApellido());
            sentencia.setString(3, paciente.getDocumento());
            sentencia.setString(4, paciente.getTelefono());
            sentencia.setString(5, paciente.getCorreo());

            // Identifica el registro que se debe actualizar.
            sentencia.setInt(6, paciente.getId());

            // Ejecuta la actualización en la base de datos.
            sentencia.executeUpdate();

            // Indica que la operación terminó sin generar una excepción.
            return true;

        } catch (SQLException e) {

            // Muestra el error si falla la actualización.
            System.out.println("Error al actualizar paciente: "
                    + e.getMessage());

            return false;
        }
    }

    /**
     * Elimina un paciente de la base de datos mediante su identificador.
     */
    public boolean eliminarPaciente(int id) {

        // Consulta SQL para eliminar el paciente seleccionado.
        String sql = "DELETE FROM pacientes WHERE id = ?";

        // Abre la conexión y prepara la consulta de eliminación.
        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            // Indica el identificador del paciente que se eliminará.
            sentencia.setInt(1, id);

            // Ejecuta la eliminación en la base de datos.
            sentencia.executeUpdate();

            // Indica que la operación terminó sin generar una excepción.
            return true;

        } catch (SQLException e) {

            // Muestra el error si falla la eliminación.
            System.out.println("Error al eliminar paciente: "
                    + e.getMessage());

            return false;
        }
    }
}
