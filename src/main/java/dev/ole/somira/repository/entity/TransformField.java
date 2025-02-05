package dev.ole.somira.repository.entity;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TransformField {

    String value();
}