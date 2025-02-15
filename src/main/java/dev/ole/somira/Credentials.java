package dev.ole.somira;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

@SuppressWarnings("unused")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class Credentials {

    private static final String CONNECT_KEY = "<KEY>";
    private static final String DATABASE_KEY = "<KEY>";
    private static final String DEFAULT_CREDENTIALS_FILE_NAME = "credentials.properties";

    String connectString;
    String database;

    private static Credentials fromStreamProperties(InputStream inputStream) {
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            return new Credentials(properties.getProperty(CONNECT_KEY), properties.getProperty(DATABASE_KEY));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String convertToEnvVarKey(String string) {
        return string.toUpperCase(Locale.ROOT).replaceFirst("\\.", "_");
    }

    public static Credentials fromResource() {
        return fromResource("/" + DEFAULT_CREDENTIALS_FILE_NAME);
    }

    public static Credentials fromResource(String resourcePath) {
        if (resourcePath == null) {
            throw new RuntimeException("Couldn't read resource from null path!");
        }
        Class<MongoManager> managerClass = MongoManager.class;
        URL resourceFile = managerClass.getResource(resourcePath);
        if (resourceFile == null) {
            return null;
        }
        try (InputStream inputStream = managerClass.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Couldn't create a stream from the resource in the path \"" + resourcePath + "\"!");
            }
            return fromStreamProperties(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read resource from path \"" + resourcePath + "\": ", e);
        }
    }

    public static Credentials fromFile() {
        return fromFile(DEFAULT_CREDENTIALS_FILE_NAME);
    }

    public static Credentials fromFile(String filePath) {
        if (filePath == null) {
            throw new RuntimeException("Couldn't read file from null path!");
        }
        File diskFile = new File(filePath);
        if (!diskFile.exists()) {
            return null;
        }
        try (InputStream inputStream = new FileInputStream(diskFile)) {
            return fromStreamProperties(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file from path \"" + filePath + "\": ", e);
        }
    }

    public static Credentials fromSystemProperties() {
        return fromSystemProperties(CONNECT_KEY, DATABASE_KEY);
    }

    public static Credentials fromSystemProperties(String propertyConnectKey, String propertyDatabaseKey) {
        return new Credentials(System.getProperty(propertyConnectKey), System.getProperty(propertyDatabaseKey));
    }

    public static Credentials fromSystemEnvVars() {
        return fromSystemEnvVars(convertToEnvVarKey(CONNECT_KEY), convertToEnvVarKey(DATABASE_KEY));
    }

    public static Credentials fromSystemEnvVars(String envVarConnectKey, String envVarDatabaseKey) {
        return new Credentials(System.getenv(envVarConnectKey), System.getenv(envVarDatabaseKey));
    }

    public static Credentials of(String connectString, String database) {
        return new Credentials(connectString, database);
    }
}
