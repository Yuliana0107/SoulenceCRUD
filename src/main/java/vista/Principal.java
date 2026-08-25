package vista;

import dao.PacienteDAO;
import modelo.Paciente;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PacienteDAO pacienteDAO = new PacienteDAO();

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    registrarPaciente();
                    break;

                case 2:
                    consultarPacientes();
                    break;

                case 3:
                    actualizarPaciente();
                    break;

                case 4:
                    eliminarPaciente();
                    break;

                case 5:
                    System.out.println("Gracias por utilizar Soulence.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);

        scanner.close();
    }

    private static void mostrarMenu() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       SISTEMA SOULENCE");
        System.out.println("     GESTIÓN DE PACIENTES");
        System.out.println("=================================");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Consultar pacientes");
        System.out.println("3. Actualizar paciente");
        System.out.println("4. Eliminar paciente");
        System.out.println("5. Salir");
        System.out.println("=================================");
    }

    private static void registrarPaciente() {

        System.out.println();
        System.out.println("===== REGISTRAR PACIENTE =====");

        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        String documento = leerTexto("Documento: ");
        String telefono = leerTexto("Teléfono: ");
        String correo = leerTexto("Correo: ");

        Paciente paciente = new Paciente(
                nombre,
                apellido,
                documento,
                telefono,
                correo
        );

        if (pacienteDAO.registrarPaciente(paciente)) {
            System.out.println("Paciente registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el paciente.");
        }
    }

    private static void consultarPacientes() {

        System.out.println();
        System.out.println("===== PACIENTES REGISTRADOS =====");

        List<Paciente> pacientes = pacienteDAO.consultarPacientes();

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    private static void actualizarPaciente() {

        System.out.println();
        System.out.println("===== ACTUALIZAR PACIENTE =====");

        int id = leerEntero("Ingrese el ID del paciente: ");

        String nombre = leerTexto("Nuevo nombre: ");
        String apellido = leerTexto("Nuevo apellido: ");
        String documento = leerTexto("Nuevo documento: ");
        String telefono = leerTexto("Nuevo teléfono: ");
        String correo = leerTexto("Nuevo correo: ");

        Paciente paciente = new Paciente(
                id,
                nombre,
                apellido,
                documento,
                telefono,
                correo
        );

        if (pacienteDAO.actualizarPaciente(paciente)) {
            System.out.println("Paciente actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el paciente.");
        }
    }

    private static void eliminarPaciente() {

        System.out.println();
        System.out.println("===== ELIMINAR PACIENTE =====");

        int id = leerEntero("Ingrese el ID del paciente: ");

        if (pacienteDAO.eliminarPaciente(id)) {
            System.out.println("Paciente eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el paciente.");
        }
    }

    private static String leerTexto(String mensaje) {

        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEntero(String mensaje) {

        while (true) {

            try {

                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Ingrese un número válido.");
            }
        }
    }
}