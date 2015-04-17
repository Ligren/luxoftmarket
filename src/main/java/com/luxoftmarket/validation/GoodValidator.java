package com.luxoftmarket.validation;

import com.luxoftmarket.domain.Good;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.luxoftmarket.domain.User;

@Component//��������� @Component = ������ ������� ��� ������� ������, ���������� �������� ����������� �� ���� ��������� � ��� ����������
public class GoodValidator implements Validator {//����������� ��������� ���������, ������� ��������� � �������


    @Override
    public boolean supports(Class<?> aClass) {
        return Good.class.isAssignableFrom(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Name is required."); // �������� ���� ��� ������ �������� �� ������
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "required.genre", "Genre is required.");


        }





    }



