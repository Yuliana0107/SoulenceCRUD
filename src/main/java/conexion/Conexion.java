package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/soulence";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "Yulist*01";

    public static Connection conectar() {

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    CONTRASENA
            );

            System.out.println("Conexion exitosa con la base de datos Soulence.");

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            System.out.println(e.getMessage());
        }

        return conexion;
    }
}
