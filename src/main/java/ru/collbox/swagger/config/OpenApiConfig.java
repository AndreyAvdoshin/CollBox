package ru.collbox.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "CollBox",
                description = "Loyalty System", version = "1.0.0",
                contact = @Contact(
                        name = "Andrey Avdoshin Dmitry Nazarov",
                        email = "mark@struchkov.dev",
                        url = "https://mark.struchkov.dev"
                )
        )
)
public class OpenApiConfig {
}
