package usrcrud.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usrcrud.model.Role;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public Role getRoleById(long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Role.class, id);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        Session session = entityManager.unwrap(Session.class);
        session.save(role);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        Session session = entityManager.unwrap(Session.class);
        Role result = getRoleById(role.getId());
        result.setRole(role.getAuthority());
        session.update(result);
    }

    @Override
    @Transactional
    public void deleteRole(long id) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(getRoleById(id));
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Role").getResultList();
    }
}
