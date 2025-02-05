package dev.ole.somira.mongodb.exception.methods;

import java.lang.reflect.Method;

public class MethodPageNotAllowedException extends Exception {

    public MethodPageNotAllowedException(Method method, Class<?> repoClass) {
        super("The method \"" + method.getName() + "\" in " +
                repoClass.getName() + " is not allowed for pagination.");
    }
}