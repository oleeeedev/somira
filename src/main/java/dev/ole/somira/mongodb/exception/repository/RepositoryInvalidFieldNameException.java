package dev.ole.somira.mongodb.exception.repository;

import dev.ole.somira.repository.entity.TransformField;

public class RepositoryInvalidFieldNameException extends Exception {

    public RepositoryInvalidFieldNameException(Class<?> typeClass, Class<?> repoClass, String fieldName) {
        super("The class " + typeClass.getName() + " used in repository " + repoClass.getName() +
                " has an invalid name for the field \"" + fieldName + "\" in the " + TransformField.class + " annotation!");
    }
}