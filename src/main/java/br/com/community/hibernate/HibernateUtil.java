package br.com.community.hibernate;
/**
 *
 * @author rian
 */
import br.com.community.entity.*;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/community");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "postgres");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
         
                configuration.addAnnotatedClass(Pessoa.class);
                configuration.addAnnotatedClass(Produto.class);
                configuration.addAnnotatedClass(Pedido.class);
                configuration.addAnnotatedClass(Usuario.class);
                configuration.addAnnotatedClass(PessoaEndereco.class);
                configuration.addAnnotatedClass(PedidoItem.class);
                configuration.addAnnotatedClass(ProdutoImagem.class);

                //configuration.addAnnotatedClass(Cidade.class);
                //configuration.addAnnotatedClass(Estado.class);
                //configuration.addAnnotatedClass(Pais.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}