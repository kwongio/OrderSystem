package com.gio.ordersystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:8080", description = "로컬 개발 서버 URL"),
                @Server(url = "https://api.ordersystem.com", description = "운영 서버 URL")
        },
        info = @Info(
                title = "주문 관리 시스템 API",
                version = "v1.0.0",
                description = "주문 생성, 조회 및 관리를 위한 API",
                termsOfService = "https://ordersystem.com/terms",
                contact = @Contact(
                        name = "Support Team",
                        email = "support@ordersystem.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI();
    }
}
