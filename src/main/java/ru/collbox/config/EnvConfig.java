package ru.collbox.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();

        String springDbUrl = dotenv.get("DB_URL");
        String pgUser = dotenv.get("DB_USERNAME");
        String pgPassword = dotenv.get("DB_PASSWORD");
        String pgDriver = dotenv.get("DB_DRIVER");
        String secret = dotenv.get("SECRET_KEY");

        System.setProperty("DB_URL", springDbUrl);
        System.setProperty("DB_USERNAME", pgUser);
        System.setProperty("DB_PASSWORD", pgPassword);
        System.setProperty("DB_DRIVER", pgDriver);
        System.setProperty("SECRET_KEY", secret);

    }
}
