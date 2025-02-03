package dev.ole.somira.repository.methods.sort;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Sort {

    public static Sort of() {
        return new Sort();
    }

    final Map<String, Boolean> fieldDirectionMap;
    int limit;
    int skip;

    private Sort() {
        this.fieldDirectionMap = new HashMap<>();
        this.limit = -1;
        this.skip = -1;
    }

    public Sort order(String fieldName, boolean ascending) {
        fieldDirectionMap.put(fieldName, ascending);
        return this;
    }

    public Sort order(String fieldName) {
        return order(fieldName, true);
    }

    public Sort limit(int limit) {
        this.limit = limit;
        return this;
    }

    public Sort skip(int skip) {
        this.skip = skip;
        return this;
    }
}