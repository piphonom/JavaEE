package ru.otus.rik.web.jaxrs;

import io.swagger.annotations.*;

@SwaggerDefinition(
        info = @Info(
                title = "Rik IT System REST API",
                description = "API to use and control Rik IT System",
                version = "1.0.0",
                termsOfService = "just use it",
                contact = @Contact(
                        name = "Alexey Mayanov", email = "piphonom@gmail.com",
                        url = "https://otus.ru"),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT")),
        tags = {
                @Tag(name = "rik_users", description = "User related operations"),
                @Tag(name = "rik_catalog", description = "Common querying operations"),
                @Tag(name = "calculator", description = "Different credit calculators"),
                @Tag(name = "soap", description = "Test SOAP services mapped to REST")
        },
        host = "localhost:8080",
        basePath = "/rik-it-system/api/v1",
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
public class DummySwaggerDefinition {
}
