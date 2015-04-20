package com.luxoftmarket.validation;

import com.luxoftmarket.domain.Good;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component//аннотация @Component = Спринг создаст бин данного класса, необходимо добавить зависимость на этот валидатор в наш контроллер
public class GoodValidator implements Validator {//имплементид интерфейс валидатор, который находится в спринге


    @Override
    public boolean supports(Class<?> aClass) {
        return Good.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Good good = (Good) o;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.good_name", "Обязательно введите имя."); // проверка того что данные значения не пустые
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "required.good_price", "Обязательно введите цену.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "required.good_amount", "Обязательно введите кол-во на складе.");
//    try { new Integer("good.getPrice()"); }catch (NumberFormatException e) { errors.rejectValue("good.getPrice()", "not_true_number", "Введите Именно число!"); }
//    try { new Integer("good.getAmount()"); }catch (NumberFormatException e) { errors.rejectValue("good.getAmount()", "not_true_number", "Введите Именно число!"); }

        }

        }









