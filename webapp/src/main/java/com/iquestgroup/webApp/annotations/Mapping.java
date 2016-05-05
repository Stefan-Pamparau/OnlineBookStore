package com.iquestgroup.webApp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to map methods to specific URL paths.
 *
 * @author Stefan Pamparau
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {
    String path() default "";

    HttpMethodType method() default HttpMethodType.GET;
}
