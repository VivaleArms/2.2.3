package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<User> getAllUsers() {
        return manager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        return manager.find(User.class, id);
    }

    @Override
    public void removeUserById(int id) {
        manager.createQuery("delete from User user WHERE user.id =:id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void saveUser(User user) {
        manager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        manager.merge(user);
    }
}