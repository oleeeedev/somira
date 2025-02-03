package dev.ole.somira.test;

import dev.ole.somira.MongoManager;
import dev.ole.somira.SettingsBuilder;
import dev.ole.somira.repository.Repository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Log
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class RepositoryTest<E, ID, R extends Repository<E, ID>> {

    MongoManager manager;
    R repository;

    @BeforeAll
    public void setup() {
        log.info("Starting Unit-Test:" + getClass().getSimpleName());
        SettingsBuilder settingsBuilder = new SettingsBuilder();

        manager = new MongoManager(null, null, settingsBuilder);

    }

    public abstract Class<R> repositoryClass();
}
