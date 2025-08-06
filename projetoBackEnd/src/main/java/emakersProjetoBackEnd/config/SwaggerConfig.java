package emakersProjetoBackEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI(){
        OpenAPI openAPI = new OpenAPI();
            Info info = new Info()
                .title("Biblioteca API")
                .version("1.0.0")
                .summary("Crud de pessoas e livros e função de emprestimo e devolução de livros")
                .description("Crud de pessoas e livros e função de emprestimo e devolução de livros");
            
        openAPI.info(info);

        return openAPI;
    }
}

//Link do swagger: http://localhost:8080/swagger-ui/index.html