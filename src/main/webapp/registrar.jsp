<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar paciente</title>
</head>

<body>

    <h1>Registrar paciente</h1>

    <form action="PacienteServlet" method="post">

        <label>Nombre:</label>
        <input type="text" name="nombre" required>

        <br><br>

        <label>Apellido:</label>
        <input type="text" name="apellido" required>

        <br><br>

        <label>Documento:</label>
        <input type="text" name="documento" required>

        <br><br>

        <label>Teléfono:</label>
        <input type="text" name="telefono" required>

        <br><br>

        <label>Correo:</label>
        <input type="email" name="correo" required>

        <br><br>

        <button type="submit">Guardar paciente</button>

    </form>

    <br>

    <a href="index.jsp">Volver al inicio</a>

</body>
</html>