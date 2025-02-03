package dev.ole.somira;

import com.mongodb.client.MongoClient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@SuppressWarnings("unused")
public class MongoManager {

    @Getter
    SettingsBuilder settingsBuilder;

    ExecutorService executorService;

    public MongoManager(Credentials credentials, ExecutorService executorService, SettingsBuilder settingsBuilder) {
        if (settingsBuilder == null) {
            settingsBuilder = new SettingsBuilder();
        }
        this.settingsBuilder = settingsBuilder;

        applyLoggerLevel();

        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        }
        this.executorService = executorService;

        if (credentials == null) {
            credentials = Credentials.fromResource();
        }
        if (credentials == null) {
            credentials = Credentials.fromResource();
        }
        if (credentials == null) {
            credentials = Credentials.fromSystemProperties();
        }
        if (credentials.getConnectString() == null || credentials.getDatabase() == null) {
            credentials = Credentials.fromSystemEnvVars();
        }

        String connectString = credentials.getConnectString();
        if (connectString == null) {
            throw new NullPointerException("No connectString given! Please make sure to provide a " +
                    "accessible connectString.");
        }
        String databaseString = credentials.getDatabase();
        if (databaseString == null) {
            throw new NullPointerException("No databaseString given! Please make sure to provide a " +
                    "accessible databaseString.");
        }
    }

    private void applyLoggerLevel() {
        Level loggerLevel = settingsBuilder.getMongoLoggerLevel();
        if (loggerLevel == null) {
            return;
        }
        Logger.getLogger("org.mongodb").setLevel(loggerLevel);
        Logger.getLogger("com.mongodb").setLevel(loggerLevel);
    }
}
