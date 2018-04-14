package dao;

import model.UserRole;

import java.util.List;

public interface UserRoleDao {

    List<UserRole> findUserRolesByUsername(String username);

    void addRole(UserRole userRole);

    void removeRole(UserRole userRole);
}
