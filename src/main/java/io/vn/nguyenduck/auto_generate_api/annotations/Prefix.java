package io.vn.nguyenduck.auto_generate_api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Prefix {
    String value() default "minecraft";
}
