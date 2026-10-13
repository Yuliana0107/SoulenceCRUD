
package configuracion;

// Importa las herramientas necesarias para configurar Spring.
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import org.springframework.context.annotation.Import;

/**
 * Configuración principal de Spring para el proyecto Soulence.
 * Aquí se conectan los controladores, servicios, repositorios
 * y las herramientas necesarias para trabajar con la base de datos.
 */
@Configuration

// Indica las carpetas donde Spring debe buscar las clases del proyecto.
@ComponentScan(basePackages = {
        "controlador", "repositorio", "modelo", "servicio"
})

// Permite que Spring encuentre los repositorios que trabajan con JPA.
@EnableJpaRepositories(basePackages = "repositorio")

// Importa la configuración de conexión con la base de datos.
@Import(JPAConfig.class)
public class SpringConfig {

    /**
     * Configura el administrador de entidades de JPA,
     * que permite trabajar con los datos almacenados en MySQL.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource) {

        // Crea la fábrica que administrará las entidades de la aplicación.
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        // Asigna la conexión con la base de datos.
        factory.setDataSource(dataSource);

        // Indica que las entidades del proyecto están en la carpeta modelo.
        factory.setPackagesToScan("modelo");

        // Configura Hibernate como herramienta para trabajar con JPA.
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Crea las propiedades de configuración de Hibernate.
        Properties properties = new Properties();

        // Especifica el dialecto para comunicarse con MySQL.
        properties.setProperty(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQL8Dialect"
        );

        // Permite actualizar la estructura de las tablas según las entidades.
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        // Permite mostrar en la consola las consultas SQL ejecutadas.
        properties.setProperty("hibernate.show_sql", "true");

        // Asigna las propiedades configuradas a la fábrica de entidades.
        factory.setJpaProperties(properties);

        // Devuelve la configuración de la fábrica de entidades.
        return factory;
    }

    /**
     * Configura el administrador de transacciones.
     * Permite coordinar las operaciones que modifican la base de datos.
     */
    @Bean
    public JpaTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {

        // Crea y devuelve el administrador de transacciones de JPA.
        return new JpaTransactionManager(entityManagerFactory);
    }
}
