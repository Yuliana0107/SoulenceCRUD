package conexion;

import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {
            System.out.println("La prueba de conexion fue exitosa.");

            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("No se pudo cerrar la conexion.");
            }
        }
    }
}