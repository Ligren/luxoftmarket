package com.luxoftmarket.dao;

import com.luxoftmarket.domain.Role;
import java.util.List;

public interface IRoleDao {
      void addRole(Role role);
//    void editRole(Role role);
//    void deleteRole(int roleId);
      Role findRole(int roleId);
//    User findRoleByName(String rolename);
//    List<Role> getAllRole();
}
