package com.pedryczpietrak.medicinedata.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfiguration {

    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Medicine Data API")
                        .description("Api providing medicines data and their statistics")
                        .version("v1.00"));
    }
}
