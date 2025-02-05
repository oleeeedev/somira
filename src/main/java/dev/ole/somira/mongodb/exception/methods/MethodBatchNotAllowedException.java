package dev.ole.somira.mongodb.exception.methods;

import dev.ole.somira.repository.methods.fields.UpdateBatch;

import java.lang.reflect.Method;

public class MethodBatchNotAllowedException extends Exception {

    public MethodBatchNotAllowedException(Method method, Class<?> repoClass) {
        super("The method \"" + method.getName() + "\" of repository " + repoClass.getName() + " is not allowed to " +
                "have the parameter " + UpdateBatch.class + "! " +
                "It's only allowed in methods, which start with the operator \"updateFieldsBy\".");
    }
}