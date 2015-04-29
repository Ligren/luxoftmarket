package com.luxoftmarket.dao;

import com.luxoftmarket.domain.User;
import java.util.List;

public interface IUserDao {
    void addUser(User user);
    void editUser(User user);
    void deleteUser(int userId);
    User findUser(int userId);
    User findUserByName(String username);
    List<User> getAllUsers();

}
