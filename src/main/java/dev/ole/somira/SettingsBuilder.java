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

    boolean appendMethodAsComment;

    boolean enableMethodProperties;

    String collectionPrefix;

    String collectionSuffix;

    public SettingsBuilder setMongoDBLoggerLevel(Level level) {
        this.mongoLoggerLevel = level;
        return this;
    }

    public SettingsBuilder disableMongoDBLogger() {
        return setMongoDBLoggerLevel(Level.OFF);
    }

    public SettingsBuilder disallowUUIDMapKeys() {
        this.disallowUUIDKeys = true;
        return this;
    }

    public SettingsBuilder enableMethodProperties() {
        this.enableMethodProperties = true;
        return this;
    }

    public SettingsBuilder disallowDiskUsageOnPagination() {
        this.allowDiskUse = false;
        return this;
    }

    public SettingsBuilder appendMethodNameAsQueryComment() {
        this.appendMethodAsComment = true;
        return this;
    }

    public SettingsBuilder collectionPrefix(String prefix) {
        this.collectionPrefix = prefix;
        return this;
    }

    public SettingsBuilder collectionSuffix(String suffix) {
        this.collectionSuffix = suffix;
        return this;
    }


    protected void merge(SettingsBuilder otherBuilder) {
        this.mongoLoggerLevel = otherBuilder.getMongoLoggerLevel();
        this.disallowUUIDKeys = otherBuilder.isDisallowUUIDKeys();
        this.allowDiskUse = otherBuilder.isAllowDiskUse();
        this.appendMethodAsComment = otherBuilder.isAppendMethodAsComment();
        this.enableMethodProperties = otherBuilder.isEnableMethodProperties();
        this.collectionPrefix = otherBuilder.getCollectionPrefix();
        this.collectionSuffix = otherBuilder.getCollectionSuffix();
    }
}
