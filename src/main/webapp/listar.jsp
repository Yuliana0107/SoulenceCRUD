
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Paciente" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de pacientes</title>
</head>

<body>

<h1>Lista de pacientes</h1>

<%
    List<Paciente> pacientes = (List<Paciente>) request.getAttribute("pacientes");
%>

<% if (pacientes != null && !pacientes.isEmpty()) { %>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Documento</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>Acciones</th>
        </tr>

        <% for (Paciente paciente : pacientes) { %>

            <tr>
                <td><%= paciente.getId() %></td>
                <td><%= paciente.getNombre() %></td>
                <td><%= paciente.getApellido() %></td>
                <td><%= paciente.getDocumento() %></td>
                <td><%= paciente.getTelefono() %></td>
                <td><%= paciente.getCorreo() %></td>
                <td>
                  <td>
                      <a href="PacienteServlet?id=<%= paciente.getId() %>">Editar</a>

                      <a href="PacienteServlet?eliminar=<%= paciente.getId() %>"
                         onclick="return confirm('¿Está seguro de eliminar este paciente?');">
                          Eliminar
                      </a>
                  </td>
                </td>
            </tr>

        <% } %>

    </table>

<% } else { %>

    <p>No hay pacientes registrados.</p>

<% } %>

<br>

<a href="registrar.jsp">Registrar nuevo paciente</a>

<br><br>

<a href="index.jsp">Volver al inicio</a>

</body>
</html>

