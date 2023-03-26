package com.ttpfx.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ttpfx
 * @date 2023/2/15
 */
public class EnumConstraintValidator implements ConstraintValidator<EnumValidate,Integer> {
    private Set<Integer> set = new HashSet<>();
    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        int[] value = constraintAnnotation.value();
        for (int i : value) {
            set.add(i);
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}
