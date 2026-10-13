
package configuracion;

// Importa la clase que permite iniciar Spring en una aplicación web.
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Inicializador de Spring para la aplicación web Soulence.
 * Configura las clases principales y las rutas que atenderá Spring.
 */
public class SpringWebInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Indica la clase que contiene la configuración principal de Spring.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {

        // Carga la configuración de componentes y base de datos.
        return new Class[]{SpringConfig.class};
    }

    /**
     * Indica la configuración adicional para el servlet de Spring.
     * En este proyecto no se utiliza una clase adicional.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {

        // Devuelve null porque no hay otra configuración de servlet.
        return null;
    }

    /**
     * Define las rutas que serán atendidas por Spring.
     */
    @Override
    protected String[] getServletMappings() {

        // Las solicitudes que comiencen por /api/ serán atendidas por Spring.
        return new String[]{"/api/*"};
    }
}
