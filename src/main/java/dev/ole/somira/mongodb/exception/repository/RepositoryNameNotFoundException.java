package dev.ole.somira.mongodb.exception.repository;

public class RepositoryNameNotFoundException extends Exception {

    public RepositoryNameNotFoundException(Class<?> repoClass, Class<?> collectionClass) {
        super("No or invalid collection name given through " + collectionClass.getName() + " annotation in " + repoClass.getName() + "! " +
                "That's a required annotation.");
    }
}