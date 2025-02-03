package dev.ole.somira;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.logging.Level;

@SuppressWarnings("unused")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class SettingsBuilder {

    Level mongoLoggerLevel;

    boolean disallowUUIDKeys;

    boolean allowDiskUse;

    public SettingsBuilder setMongoDBLoggerLevel(Level level) {
        this.mongoLoggerLevel = level;
        return this;
    }

    protected void merge(SettingsBuilder otherBuilder) {
        this.mongoLoggerLevel = otherBuilder.mongoLoggerLevel;

    }
}
