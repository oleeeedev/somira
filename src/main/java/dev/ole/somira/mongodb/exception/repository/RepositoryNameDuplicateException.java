package dev.ole.somira.mongodb.exception.repository;

public class RepositoryNameDuplicateException extends Exception {

    public RepositoryNameDuplicateException(Class<?> repoClass, Class<?> collectionClass) {
        super("Duplicated collection name given through " + collectionClass.getName() + " annotation in " + repoClass.getName() + "! " +
                "That's not allowed.");
    }
}