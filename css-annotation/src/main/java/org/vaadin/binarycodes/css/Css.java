package org.vaadin.binarycodes.css;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Css {
    Property[] properties() default {};

    String[] classNames() default {};

    String styleCss() default "";

    String styleCssPath() default "";
}
