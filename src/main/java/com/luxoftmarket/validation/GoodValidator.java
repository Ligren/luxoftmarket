package com.luxoftmarket.validation;

import com.luxoftmarket.domain.Good;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GoodValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Good.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Обязательно введите имя."); // проверка того что данные значения не пустые
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required.price", "Обязательно введите цену.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "required.amoun", "Обязательно введите кол-во на складе.");
    }

}









