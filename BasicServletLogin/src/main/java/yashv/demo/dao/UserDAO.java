package yashv.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import yashv.demo.dto.User;

public class UserDAO {
    public void insertUser(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("User_details");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(user);
        em.detach(user);

        em.getTransaction().commit();
        emf.close();
        em.close();
    }

    public User findUserByUname(String uname) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("User_details");
        EntityManager em = emf.createEntityManager();

        User result = em.find(User.class, uname);
        em.detach(result);

        emf.close();
        em.close();
        return result;
    }

    public User findAndDeleteByUname(String uname) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("User_details");
        EntityManager em = emf.createEntityManager();

        User result = em.find(User.class, uname);
        em.remove(result);

        emf.close();
        em.close();
        return result;
    }

    public User findAndUpdateByUname(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("User_details");
        EntityManager em = emf.createEntityManager();

        User result = em.find(User.class, user.getUname());
        result.setPassword(user.getPassword());
        em.detach(result);

        emf.close();
        em.close();
        return result;
    }
}
