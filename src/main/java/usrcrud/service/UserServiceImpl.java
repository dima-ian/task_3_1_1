package usrcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usrcrud.dao.UserDAO;
import usrcrud.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public List<User> allUsers() {  return userDAO.allUsers( );  }

    @Override
    @Transactional
    public void addUser(User user) {  userDAO.addUser(user);  }

    @Override
    @Transactional
    public void deleteUser(Long id) {  userDAO.deleteUser(id);  }

    @Override
    @Transactional
    public void editUser(User user) {  userDAO.editUser(user);  }

    @Override
    @Transactional
    public User getUserById(Long id) {  return userDAO.getUserById(id);  }

    @Override
    @Transactional
    public User getUserByUserName(String userName) {
        return userDAO.getUserByUserName(userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
