package dev.ole.somira.repository.entity.compound;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(CompoundIndexArray.class)
public @interface CompoundIndex {

    Index[] value();

    boolean uniqueIndex() default false;
}