package com.example.blog.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RAnnotation {
	String key();
	int time() default 1 ;
}
