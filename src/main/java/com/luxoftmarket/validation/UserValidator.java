package com.luxoftmarket.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.luxoftmarket.domain.User;
@Component //аннотация @Component = Спринг создаст бин данного класса, необходимо добавить зависимость на этот валидатор в наш контроллер
public class UserValidator { //имплементид интерфейс валидатор, который находится в спринге



    /**
     * необходимо добавить зависимость на наш контроллер BookController
     * http://habrahabr.ru/post/68318/
     * необходимо добавить сканирование нашего нового пакета validation
     * <context:component-scan base-package="com.bookmanager.repository, com.bookmanager.validation"/>
     * т.е. спринг автоматически сканирует бины из пакета:
     * <context:component-scan base-package="com.bookmanager.repository, com.bookmanager.validation"/>
     * которые помечены аннотацией
     * @Component
     */

/*
        @Override
        public boolean supports(Class<?> aClass) {
            return User.class.isAssignableFrom(aClass);
        }
        */
    /*

        @Override
        public void validate(Object o, Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Name is required."); // проверка того что данные значения не пустые
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "required.genre", "Genre is required.");


        }
*/
}
