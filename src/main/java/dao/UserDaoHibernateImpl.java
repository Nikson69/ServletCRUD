package dao;

import models.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDAO {
    SessionFactory sf;
    public UserDaoHibernateImpl(SessionFactory sf) {
        this.sf = sf;
    }

    public User findById(long id) {
        Session session = sf.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public void save(User user) {
        Session session = sf.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = sf.openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sf.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Session session = sf.openSession();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        List<User> users = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return users;
    }

    @Override
    public User authorize(String login) {
        Session session = sf.openSession();
        Criteria userCriteria = session.createCriteria(User.class);
        userCriteria.add(Restrictions.eq("login", login));
        User user = (User) userCriteria.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public boolean findByLogin(String login) {
        Session session = sf.openSession();
        Criteria userCriteria = session.createCriteria(User.class);
        userCriteria.add(Restrictions.eq("login", login));
        User user = (User) userCriteria.uniqueResult();
        session.close();
        return user != null;
    }
}
