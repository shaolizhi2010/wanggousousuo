package com.scaffold.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface MaxLength {
	 String value() default "200";
}
