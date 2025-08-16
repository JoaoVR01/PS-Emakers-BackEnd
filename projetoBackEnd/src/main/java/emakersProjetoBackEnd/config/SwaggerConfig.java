package emakersProjetoBackEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
     public OpenAPI customOpenAPI() {
         final String schemeName = "bearerAuth";

        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList(schemeName))
            .components(new Components().addSecuritySchemes(
                schemeName,
                new SecurityScheme()
                    .name(schemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            ))
            .info(new Info()
                .title("API de Biblioteca")
                .description("API para gerenciamento de livros, usuários e empréstimos.")
                .summary("API que realiza o CRUD de Livros e de Pessoas, possui função de emprestar e devolver livros para os usuarios logados através de autenticação com JWT")
                .version("1.0.0")
                .contact(new Contact()
                    .name("João Vitor Rezende")
                    .email("joao.marciano@estudante.ufla.br")
                    .url("https://github.com/JoaoVR01/PS-Emakers-BackEnd")));
    }
}


//Link do swagger: http://localhost:8080/swagger-ui/index.html