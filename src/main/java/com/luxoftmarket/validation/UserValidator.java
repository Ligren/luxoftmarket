package com.luxoftmarket.validation;

import com.luxoftmarket.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "required.nick", "required.nick");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "required.nick");
    }

}