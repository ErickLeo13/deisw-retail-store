package pe.edu.upc.retailstore.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApi configuration class.
 */
@Configuration
public class OpenApiConfiguration {

  /**
   * OpenApi configuration.
   *
   * @return OpenAPI
   */
  @Bean
  public OpenAPI learningPlatformOpenApi() {
    // General configuration
    var openApi = new OpenAPI();
    openApi
        .info(new Info()
            .title("Retail Store API")
            .description("Retail Store Platform application REST API documentation.")
            .version("v1.0.0")
            .license(new License().name("Apache 2.0")
                .url("https://springdoc.org")))
        .externalDocs(new ExternalDocumentation()
            .description("Retail Store Platform Documentation")
            .url("https://github.com/upc-is-si732"));

    // Return OpenAPI configuration object with all the settings
    return openApi;
  }
}