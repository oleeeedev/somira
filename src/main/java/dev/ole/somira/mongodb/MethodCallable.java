package dev.ole.somira.mongodb;

@FunctionalInterface
public interface MethodCallable {

    Object call() throws Exception;
}
