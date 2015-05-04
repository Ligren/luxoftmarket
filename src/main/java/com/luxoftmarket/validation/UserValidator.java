package com.luxoftmarket.validation;

import com.luxoftmarket.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component//��������� @Component = ������ ������� ��� ������� ������, ���������� �������� ����������� �� ���� ��������� � ��� ����������
public class UserValidator implements Validator {//����������� ��������� ���������, ������� ��������� � �������


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
//        Good good = (Good) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "required.nick", "required.nick"); // �������� ���� ��� ������ �������� �� ������
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.good_price", "����������� ������� ����.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "required.nick");

//        try { new Integer("price"); }catch (NumberFormatException e) { errors.rejectValue("price", "not_true_number", "������� ������ �����!"); }
//        try { new Integer("amount"); }catch (NumberFormatException e) { errors.rejectValue("amount", "not_true_number", "������� ������ �����!"); }

    }

}