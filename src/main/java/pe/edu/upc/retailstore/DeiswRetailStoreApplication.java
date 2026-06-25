package pe.edu.upc.retailstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main class of the application.
 */
@SpringBootApplication
@EnableJpaAuditing
public class DeiswRetailStoreApplication {

  /**
   * Main method.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(DeiswRetailStoreApplication.class, args);
  }
}
