package dev.ole.somira.mongodb.exception.methods;

import java.lang.reflect.Method;

public class MethodUnsupportedException extends Exception {

    public MethodUnsupportedException(Method method, Class<?> repoClass) {
        super("Method \"" + method.getName() + "\" of repository " + repoClass.getName() + " is not supported by somira!");
    }
}