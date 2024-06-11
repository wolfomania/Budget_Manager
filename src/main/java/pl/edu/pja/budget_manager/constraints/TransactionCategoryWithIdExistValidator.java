package pl.edu.pja.budget_manager.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.repositories.TransactionCategoryRepository;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TransactionCategoryWithIdExistValidator implements ConstraintValidator<TransactionCategoryWithIdExist, Long> {

    final TransactionCategoryRepository transactionCategoryRepository;

    String message;

    @Override
    public void initialize(TransactionCategoryWithIdExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(transactionCategoryRepository.findById(value).isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
