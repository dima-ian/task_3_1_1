package usrcrud.service;

import usrcrud.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleById(long id);

    void addRole(Role role);

    void editRole(Role role);

    void deleteRole(long id);

    List<Role> getRoles();
}
