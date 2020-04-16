package usrcrud.dao;

import usrcrud.model.Role;

import java.util.List;

public interface RoleDao {

    Role getRoleById(long id);

    void addRole(Role role);

    void editRole(Role role);

    void deleteRole(long id);

    List<Role> getRoles();

}
