package ru.collbox.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "CollBox",
                description = "Yet Another Money Tracker", version = "0.0.1",
                contact = @Contact(
                        name = "Andrey Avdoshin, Dmitry Nazarov",
                        email = "mark@struchkov.dev"
                )
        )
)
public class OpenApiConfig {
}
