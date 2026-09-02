package controlador;

import dao.PacienteDAO;
import modelo.Paciente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String eliminar = request.getParameter("eliminar");

        if (eliminar != null && !eliminar.isEmpty()) {

            PacienteDAO dao = new PacienteDAO();

            boolean eliminado = dao.eliminarPaciente(Integer.parseInt(eliminar));

            if (eliminado) {
                System.out.println("Paciente eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el paciente.");
            }

            response.sendRedirect("PacienteServlet");
            return;
        }
        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {

            PacienteDAO dao = new PacienteDAO();

            List<Paciente> pacientes = dao.consultarPacientes();

            for (Paciente paciente : pacientes) {

                if (paciente.getId() == Integer.parseInt(id)) {

                    request.setAttribute("paciente", paciente);

                    request.getRequestDispatcher("editar.jsp")
                            .forward(request, response);

                    return;
                }
            }

        } else {

            PacienteDAO dao = new PacienteDAO();

            List<Paciente> pacientes = dao.consultarPacientes();

            request.setAttribute("pacientes", pacientes);

            request.getRequestDispatcher("listar.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        Paciente paciente = new Paciente();

        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setDocumento(documento);
        paciente.setTelefono(telefono);
        paciente.setCorreo(correo);

        PacienteDAO dao = new PacienteDAO();

        if (id == null || id.isEmpty()) {

            boolean registrado = dao.registrarPaciente(paciente);

            if (registrado) {
                System.out.println("Paciente registrado correctamente.");
            } else {
                System.out.println("No se pudo registrar el paciente.");
            }

        } else {

            paciente.setId(Integer.parseInt(id));

            boolean actualizado = dao.actualizarPaciente(paciente);

            if (actualizado) {
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el paciente.");
            }
        }

        response.sendRedirect("PacienteServlet");
    }
}
