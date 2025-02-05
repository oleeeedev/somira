package dev.ole.somira.mongodb.exception.methods;

import java.lang.reflect.Method;

public class MethodInvalidSortSkipException extends Exception {

    public MethodInvalidSortSkipException(Method method, Class<?> repoClass) {
        super("You're not allowed to use a skip size of less or equal to \"0\"!" +
                "You tried to execute the  method \"" + method.getName() + "\" of the repository " + repoClass.getName() + ". " +
                "Please check the execution parameters.");
    }
}