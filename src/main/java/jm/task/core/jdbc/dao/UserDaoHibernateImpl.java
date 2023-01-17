package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.sessionFactory.openSession()) {
            System.out.println("Начинаю процесс создания таблицы");
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS `java_pre-project_1.1.4`.`TABLE_OF_USERS`  (\n" +
                    "                        `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "                         `name` VARCHAR(100) NULL,\n" +
                    "                         `lastName` VARCHAR(100) NULL,\n" +
                    "                         `age` TINYINT NULL,\n" +
                    "                         PRIMARY KEY (`id`) )").executeUpdate();
            System.out.println("Создание таблицы завершено");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS TABLE_OF_USERS").executeUpdate();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);
            List<User> ans =  session.createQuery(criteria).getResultList();
            transaction.commit();
            return ans;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        }
    }
}
