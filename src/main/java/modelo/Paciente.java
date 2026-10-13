
package modelo;

// Importa las anotaciones de JPA para relacionar esta clase con la base de datos.
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Modelo que representa a un paciente dentro del sistema Soulence.
 * Sus atributos corresponden a los datos almacenados en la tabla pacientes.
 */

// Indica que esta clase representa una entidad que se guarda en la base de datos.
@Entity

// Establece el nombre de la tabla asociada a esta entidad.
@Table(name = "pacientes")
public class Paciente {

    // Identifica de forma única a cada paciente.
    @Id

    // Permite que la base de datos genere automáticamente el identificador.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Almacena el nombre del paciente.
    private String nombre;

    // Almacena el apellido del paciente.
    private String apellido;

    // Almacena el número de documento del paciente.
    private String documento;

    // Almacena el número de teléfono del paciente.
    private String telefono;

    // Almacena el correo electrónico del paciente.
    private String correo;

    // Constructor vacío requerido por JPA para crear objetos de esta entidad.
    public Paciente() {
    }

    // Constructor para crear un paciente sin indicar su ID.
    public Paciente(String nombre, String apellido, String documento,
                    String telefono, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Constructor para crear un paciente indicando también su ID.
    public Paciente(int id, String nombre, String apellido, String documento,
                    String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Devuelve el identificador del paciente.
    public int getId() {
        return id;
    }

    // Permite establecer el identificador del paciente.
    public void setId(int id) {
        this.id = id;
    }

    // Devuelve el nombre del paciente.
    public String getNombre() {
        return nombre;
    }

    // Permite asignar el nombre del paciente.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Devuelve el apellido del paciente.
    public String getApellido() {
        return apellido;
    }

    // Permite asignar el apellido del paciente.
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Devuelve el número de documento del paciente.
    public String getDocumento() {
        return documento;
    }

    // Permite asignar el número de documento del paciente.
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    // Devuelve el teléfono del paciente.
    public String getTelefono() {
        return telefono;
    }

    // Permite asignar el teléfono del paciente.
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Devuelve el correo electrónico del paciente.
    public String getCorreo() {
        return correo;
    }

    // Permite asignar el correo electrónico del paciente.
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Devuelve una representación del paciente en forma de texto.
    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
