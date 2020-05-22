package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME) // runtime visibility
@Target(ElementType.TYPE) // applicable to types(classes)
public @interface JsonSerializable {
}
