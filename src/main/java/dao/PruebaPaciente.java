package dao;

public class PruebaPaciente {

    public static void main(String[] args) {

        PacienteDAO pacienteDAO = new PacienteDAO();

        int idPaciente = 1;

        boolean eliminado = pacienteDAO.eliminarPaciente(idPaciente);

        if (eliminado) {
            System.out.println("Paciente eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el paciente.");
        }
    }
}