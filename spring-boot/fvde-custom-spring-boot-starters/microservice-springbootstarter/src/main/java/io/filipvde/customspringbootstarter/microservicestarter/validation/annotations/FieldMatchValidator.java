package io.filipvde.customspringbootstarter.microservicestarter.validation.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private static final Logger log = LoggerFactory.getLogger(FieldMatchValidator.class);

    private String firstField;

    private String secondField;

    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstField = constraintAnnotation.first();
        secondField = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        boolean valid = true;

        try {
            final Object firstProperty = BeanUtils.getProperty(obj, firstField);
            final Object secondProperty = BeanUtils.getProperty(obj, secondField);

            valid = (firstProperty == null && secondProperty == null)
                    || (firstProperty != null && firstProperty.equals(secondProperty));
        } catch (final Exception e) {
            log.warn("Error while validating fields {} - {}", this.getClass().getName(), e.getMessage());
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}