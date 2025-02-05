package dev.ole.somira.mongodb.exception.repository;

public class RepositoryGetterNotFoundException extends Exception {

    public RepositoryGetterNotFoundException(Class<?> typeClass, Class<?> repoClass, String fieldName) {
        super("The class " + typeClass.getName() + " used in repository " + repoClass.getName() + " " +
                "uses a invalid or no getter method for the field \"" + fieldName + "\"!");
    }
}