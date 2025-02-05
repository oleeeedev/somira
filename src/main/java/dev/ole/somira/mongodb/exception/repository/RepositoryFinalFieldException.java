package dev.ole.somira.mongodb.exception.repository;

import java.lang.reflect.Field;

public class RepositoryFinalFieldException extends Exception {

    public RepositoryFinalFieldException(Field field, Class<?> repoClass) {
        super("Field modifier final found on field \"" + field.getName() + "\" in entity of repository " +
                repoClass.getName() + ". That's not allowed!");
    }
}