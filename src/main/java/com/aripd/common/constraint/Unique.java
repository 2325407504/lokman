package com.aripd.common.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validates whether an input field already exists in the database or not
 * 
 * Example, to check account
 * @Unique(entity=Account.class)
 * 
 * @author cem
 *
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueIDValidator.class)
@Documented
public @interface Unique {
    String message() default "{com.omgo.security.domain.validator.constraints.uniques}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * The mapped hibernate/jpa entity class
     */
    Class<?> entity();

    /**
     * The property of the entity we want to validate for uniqueness. Default name is "id"
     */
    String property() default "id";
}