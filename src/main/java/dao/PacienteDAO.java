package dao;

import conexion.Conexion;
import modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // INSERTAR PACIENTE
    public boolean registrarPaciente(Paciente paciente) {

        String sql = "INSERT INTO pacientes " +
                "(nombre, apellido, documento, telefono, correo) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, paciente.getNombre());
            sentencia.setString(2, paciente.getApellido());
            sentencia.setString(3, paciente.getDocumento());
            sentencia.setString(4, paciente.getTelefono());
            sentencia.setString(5, paciente.getCorreo());

            sentencia.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al registrar paciente: "
                    + e.getMessage());

            return false;
        }
    }

    // CONSULTAR PACIENTES
    public List<Paciente> consultarPacientes() {

        List<Paciente> pacientes = new ArrayList<>();

        String sql = "SELECT * FROM pacientes";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(resultado.getInt("id"));
                paciente.setNombre(resultado.getString("nombre"));
                paciente.setApellido(resultado.getString("apellido"));
                paciente.setDocumento(resultado.getString("documento"));
                paciente.setTelefono(resultado.getString("telefono"));
                paciente.setCorreo(resultado.getString("correo"));

                pacientes.add(paciente);
            }

        } catch (SQLException e) {

            System.out.println("Error al consultar pacientes: "
                    + e.getMessage());
        }

        return pacientes;
    }

    // ACTUALIZAR PACIENTE
    public boolean actualizarPaciente(Paciente paciente) {

        String sql = "UPDATE pacientes SET " +
                "nombre = ?, apellido = ?, documento = ?, " +
                "telefono = ?, correo = ? WHERE id = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, paciente.getNombre());
            sentencia.setString(2, paciente.getApellido());
            sentencia.setString(3, paciente.getDocumento());
            sentencia.setString(4, paciente.getTelefono());
            sentencia.setString(5, paciente.getCorreo());
            sentencia.setInt(6, paciente.getId());

            sentencia.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al actualizar paciente: "
                    + e.getMessage());

            return false;
        }
    }

    // ELIMINAR PACIENTE
    public boolean eliminarPaciente(int id) {

        String sql = "DELETE FROM pacientes WHERE id = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al eliminar paciente: "
                    + e.getMessage());

            return false;
        }
    }
}