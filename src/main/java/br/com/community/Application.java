package br.com.community;

/**
 *
 * @author rian
 */
import br.com.community.view.server.Controlador;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

@EntityScan(basePackages = {
    "br.com.community.entity"
})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws FileNotFoundException {
        System.setOut(new PrintStream(new File("./log.txt")));
        Controlador controlador = new Controlador();
        controlador.setVisible(true);
        SpringApplication.run(applicationClass, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(applicationClass);
    }

    private static Class<Application> applicationClass = Application.class;
}
