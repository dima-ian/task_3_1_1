package usrcrud.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usrcrud.model.User;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {


    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> allUsers() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from User").list();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(getUserById(id));
    }

    @Override
    @Transactional
    public void editUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public User getUserByUserName(String userName) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM User where login = :paramName");
        query.setParameter("paramName", userName);
        return (User) query.getSingleResult();
    }
}
