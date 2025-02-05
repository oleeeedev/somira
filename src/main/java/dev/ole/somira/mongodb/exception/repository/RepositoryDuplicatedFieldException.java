package dev.ole.somira.mongodb.exception.repository;

import java.lang.reflect.Field;

public class RepositoryDuplicatedFieldException extends Exception {

    public RepositoryDuplicatedFieldException(Field field, Class<?> repoClass) {
        super("Duplicated fields found with name \"" + field.getName() + "\" in entity of repository " +
                repoClass.getName() + "!");
    }
}