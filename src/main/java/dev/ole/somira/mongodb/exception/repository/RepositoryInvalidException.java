package dev.ole.somira.mongodb.exception.repository;


import dev.ole.somira.repository.Repository;

public class RepositoryInvalidException extends Exception {

    public RepositoryInvalidException(Class<?> repoClass) {
        super("The class of " + repoClass.getName() + " is not from type " + Repository.class.getName() + "!");
    }
}