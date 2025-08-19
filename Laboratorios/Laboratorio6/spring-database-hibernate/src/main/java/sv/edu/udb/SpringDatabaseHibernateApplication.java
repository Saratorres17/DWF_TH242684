package sv.edu.udb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
		scanBasePackages = "sv.edu.udb",
		exclude = HibernateJpaAutoConfiguration.class // evita conflicto con tu SessionFactory
)
public class SpringDatabaseHibernateApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDatabaseHibernateApplication.class, args);
	}
}
