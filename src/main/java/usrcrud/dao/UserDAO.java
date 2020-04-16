package usrcrud.dao;

import usrcrud.model.User;

import java.util.List;

public interface UserDAO {

    List<User> allUsers();
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    User getUserById(Long id);
    User getUserByUserName(String userName);


}
