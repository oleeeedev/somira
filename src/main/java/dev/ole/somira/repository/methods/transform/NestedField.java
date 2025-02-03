package dev.ole.somira.repository.methods.transform;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(NestedField.NestedKeyArray.class)
public @interface NestedField {

    String key();

    String query();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface NestedKeyArray {

        NestedField[] value();
    }
}