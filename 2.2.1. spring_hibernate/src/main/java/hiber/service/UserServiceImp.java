package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

//   @Transactional
//   public User getUserByCarModelAndSeries(String model, int series) {
//      Session session = entityManager.unwrap(Session.class);
//      Query<User> query = session.createQuery(
//                      "SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series", User.class)
//              .setParameter("model", model)
//              .setParameter("series", series);
//      return query.uniqueResult();
//   }

    @Override
    @Transactional
    public List<User> getUsersByCarModelAndSeries(String model, int series) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u JOIN FETCH u.car c WHERE c.model = :model AND c.series = :series"
        );
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList();
    }
}
