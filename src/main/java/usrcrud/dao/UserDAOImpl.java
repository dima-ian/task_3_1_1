package usrcrud.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usrcrud.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    //@Autowired
    @PersistenceContext
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
//        Session session = entityManager.unwrap(Session.class);
//        Query query = session.createQuery("FROM User where login = :paramName");
        Query query = entityManager.createQuery("FROM User where login = :paramName");
        query.setParameter("paramName", userName);
        return (User) query.getSingleResult();
    }


    public User findByUserName(String theUserName) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // now retrieve/read from database using username
        org.hibernate.query.Query<User> theQuery = currentSession.createQuery("from User where login = :uName", User.class);
        theQuery.setParameter("uName", theUserName);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }
}
