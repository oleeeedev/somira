package dev.ole.somira.mongodb.exception.repository;

public class RepositoryIndexFieldNotFoundException extends Exception {

    public RepositoryIndexFieldNotFoundException(Class<?> repoClass, String indexName) {
        super("Couldn't find " + indexName + " field in entity of " + repoClass.getName() + " to index.");
    }
}