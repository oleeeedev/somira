package dev.ole.somira;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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
}
