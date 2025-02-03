package dev.ole.somira.operators;

import java.lang.reflect.Method;

@FunctionalInterface
public interface ReturnTypeValidator {

    void check(Method method, Class<?> returnType, Class<?> entityClass, Class<?> repoClass) throws Exception;
}
