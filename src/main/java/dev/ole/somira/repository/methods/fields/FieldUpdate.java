package dev.ole.somira.repository.methods.fields;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldUpdate {

    public static FieldUpdate set(String fieldName, Object defaultValue) {
        return new FieldUpdate(UpdateType.SET, fieldName, defaultValue);
    }

    public static FieldUpdate rename(String fieldName, String value) {
        return new FieldUpdate(UpdateType.RENAME, fieldName, value);
    }

    public static FieldUpdate remove(String fieldName) {
        return new FieldUpdate(UpdateType.REMOVE, fieldName, null);
    }

    UpdateType updateType;
    String fieldName;

    Object value;
}