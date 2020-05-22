package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME) // visibility is runtime
@Target(ElementType.FIELD) // applicable to class fields
public @interface JsonElement {
    public String key() default "";
}
