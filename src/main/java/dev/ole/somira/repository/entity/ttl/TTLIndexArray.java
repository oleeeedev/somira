package dev.ole.somira.repository.entity.ttl;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TTLIndexArray {

    TTLIndex[] value();
}