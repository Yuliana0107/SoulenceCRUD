
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Paciente" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar paciente</title>
</head>

<body>

<h1>Editar paciente</h1>

<%
    Paciente paciente = (Paciente) request.getAttribute("paciente");
%>

<form action="PacienteServlet" method="post">

    <input type="hidden" name="id" value="<%= paciente.getId() %>">

    <label>Nombre:</label>
    <input type="text" name="nombre"
           value="<%= paciente.getNombre() %>" required>

    <br><br>

    <label>Apellido:</label>
    <input type="text" name="apellido"
           value="<%= paciente.getApellido() %>" required>

    <br><br>

    <label>Documento:</label>
    <input type="text" name="documento"
           value="<%= paciente.getDocumento() %>" required>

    <br><br>

    <label>Teléfono:</label>
    <input type="text" name="telefono"
           value="<%= paciente.getTelefono() %>" required>

    <br><br>

    <label>Correo:</label>
    <input type="email" name="correo"
           value="<%= paciente.getCorreo() %>" required>

    <br><br>

    <button type="submit">Actualizar paciente</button>

</form>

<br>

<a href="PacienteServlet">Volver a la lista</a>

</body>
</html>

