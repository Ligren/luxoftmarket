package com.luxoftmarket.service;

import com.luxoftmarket.domain.User;

import java.util.List;

public interface IUserService {
    void add(User user);
    void edit(User user);
    void delete(int id);
    User getUser(int id);
    List getAllUser();
}
