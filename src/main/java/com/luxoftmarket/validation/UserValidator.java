package com.luxoftmarket.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.luxoftmarket.domain.User;
@Component //��������� @Component = ������ ������� ��� ������� ������, ���������� �������� ����������� �� ���� ��������� � ��� ����������
public class UserValidator { //����������� ��������� ���������, ������� ��������� � �������



    /**
     * ���������� �������� ����������� �� ��� ���������� BookController
     * http://habrahabr.ru/post/68318/
     * ���������� �������� ������������ ������ ������ ������ validation
     * <context:component-scan base-package="com.bookmanager.repository, com.bookmanager.validation"/>
     * �.�. ������ ������������� ��������� ���� �� ������:
     * <context:component-scan base-package="com.bookmanager.repository, com.bookmanager.validation"/>
     * ������� �������� ����������
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
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Name is required."); // �������� ���� ��� ������ �������� �� ������
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "required.genre", "Genre is required.");


        }
*/
}
