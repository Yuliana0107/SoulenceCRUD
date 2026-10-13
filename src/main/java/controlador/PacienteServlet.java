
package controlador;

// Importa la clase que realiza las operaciones con los pacientes.
import dao.PacienteDAO;

// Importa el modelo que representa los datos de cada paciente.
import modelo.Paciente;

// Importa las clases necesarias para manejar solicitudes web.
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet encargado de gestionar las solicitudes relacionadas
 * con los pacientes de la aplicación Soulence.
 */
@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {

    /**
     * Atiende las solicitudes GET.
     * Permite consultar pacientes, abrir el formulario de edición
     * y eliminar registros cuando se solicita esa operación.
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene el identificador del paciente que se desea eliminar.
        String eliminar = request.getParameter("eliminar");

        // Comprueba si se recibió una solicitud para eliminar un paciente.
        if (eliminar != null && !eliminar.isEmpty()) {

            // Crea el objeto que permite acceder a los datos de los pacientes.
            PacienteDAO dao = new PacienteDAO();

            // Convierte el identificador en número y elimina el registro.
            boolean eliminado =
                    dao.eliminarPaciente(Integer.parseInt(eliminar));

            // Informa en la consola si la eliminación fue exitosa.
            if (eliminado) {
                System.out.println("Paciente eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el paciente.");
            }

            // Regresa a la lista de pacientes.
            response.sendRedirect("PacienteServlet");
            return;
        }

        // Obtiene el identificador del paciente que se desea editar.
        String id = request.getParameter("id");

        // Comprueba si se recibió un identificador para editar.
        if (id != null && !id.isEmpty()) {

            // Crea el objeto encargado de consultar los pacientes.
            PacienteDAO dao = new PacienteDAO();

            // Obtiene la lista de pacientes almacenados.
            List<Paciente> pacientes = dao.consultarPacientes();

            // Recorre la lista para encontrar el paciente solicitado.
            for (Paciente paciente : pacientes) {

                // Compara el identificador de cada paciente con el recibido.
                if (paciente.getId() == Integer.parseInt(id)) {

                    // Envía los datos del paciente al formulario de edición.
                    request.setAttribute("paciente", paciente);

                    // Abre editar.jsp con la información del paciente.
                    request.getRequestDispatcher("editar.jsp")
                            .forward(request, response);

                    return;
                }
            }

        } else {

            // Crea el objeto encargado de consultar los pacientes.
            PacienteDAO dao = new PacienteDAO();

            // Consulta todos los pacientes registrados.
            List<Paciente> pacientes = dao.consultarPacientes();

            // Envía la lista a la página que muestra los registros.
            request.setAttribute("pacientes", pacientes);

            // Abre listar.jsp para mostrar los pacientes.
            request.getRequestDispatcher("listar.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Atiende las solicitudes POST.
     * Permite registrar pacientes nuevos o actualizar los existentes.
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene el identificador; si está vacío, se trata de un registro nuevo.
        String id = request.getParameter("id");

        // Obtiene los datos enviados desde el formulario.
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        // Crea un objeto para almacenar los datos recibidos.
        Paciente paciente = new Paciente();

        // Asigna al objeto la información ingresada en el formulario.
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setDocumento(documento);
        paciente.setTelefono(telefono);
        paciente.setCorreo(correo);

        // Crea el objeto que ejecutará la operación en la base de datos.
        PacienteDAO dao = new PacienteDAO();

        // Si no hay identificador, registra un paciente nuevo.
        if (id == null || id.isEmpty()) {

            // Envía los datos para registrar el paciente.
            boolean registrado = dao.registrarPaciente(paciente);

            // Informa en la consola el resultado del registro.
            if (registrado) {
                System.out.println("Paciente registrado correctamente.");
            } else {
                System.out.println("No se pudo registrar el paciente.");
            }

        } else {

            // Asigna el identificador al paciente que se va a actualizar.
            paciente.setId(Integer.parseInt(id));

            // Envía los datos para actualizar el registro existente.
            boolean actualizado = dao.actualizarPaciente(paciente);

            // Informa en la consola el resultado de la actualización.
            if (actualizado) {
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el paciente.");
            }
        }

        // Regresa al servlet para mostrar nuevamente la lista de pacientes.
        response.sendRedirect("PacienteServlet");
    }
}
