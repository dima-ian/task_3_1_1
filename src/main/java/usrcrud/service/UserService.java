package usrcrud.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import usrcrud.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> allUsers();
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    User getUserById(Long id);
    User getUserByUserName(String userName);
    User findByUserName(String userName);

}
