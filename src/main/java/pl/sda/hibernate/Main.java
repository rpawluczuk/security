package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = SessionManager.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.close();

        updateQuery();
        SessionManager.getSessionFactory().close();
    }

    public static void perist() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = new Costumer();
        session.persist(costumer);
        costumer.setName("Franek1");
        costumer.setName("232");
        session.getTransaction().commit();
        session.close();
    }

    public static void save() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = new Costumer();
        costumer.setName("Franek2");
        Long id = (Long) session.save(costumer);
        costumer.setName(id + costumer.getName());
        session.getTransaction().commit();
        session.close();
    }

    public static void find() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = session.find(Costumer.class, 1L);
        System.out.println(costumer);
        session.getTransaction().commit();
        session.close();
    }

    public static void findAndChange() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = session.find(Costumer.class, 1L);
        costumer.setName("Dupa");
        session.getTransaction().commit();
        session.close();
    }

    public static void findByName(String name) {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Costumer> query = session.createQuery("select c from Costumer c " +
                "where c.name = :value", Costumer.class);
        query.setParameter("value", name);
        List<Costumer> list = query.list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }

    public static void findByNameNamedQuery(String name) {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Costumer> query = session
                .createNamedQuery("selectByName", Costumer.class);
        query.setParameter("value", name);
        List<Costumer> list = query.list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }

    public static void updateQuery() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Costumer " +
                "set name = :valueForName " +
                "where name like :value1 or id = :value2");
        query.setParameter("valueForName", "Przemio");
        query.setParameter("value1", "6%");
        query.setParameter("value2", 10L);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


}
