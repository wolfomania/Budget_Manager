package pl.edu.pja.budget_manager.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TransactionCategoryWithIdExistValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionCategoryWithIdExist {

    String message() default "Currency with this id does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
