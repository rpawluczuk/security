package pl.sda.hibernate;

import org.hibernate.Session;

import java.io.Serializable;

public class Main {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = SessionManager.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.close();

        perist();
        save();
        find();
        findAndChange();
        SessionManager.getSessionFactory().close();
    }
    public static void perist(){
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = new Costumer();
        session.persist(costumer);
        costumer.setName("Franek1");
        costumer.setName("232");
        session.getTransaction().commit();
        session.close();
    }

    public static void save(){
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = new Costumer();
        costumer.setName("Franek2");
        Long id = (Long) session.save(costumer);
        costumer.setName(id + costumer.getName());
        session.getTransaction().commit();
        session.close();
    }

    public static void find(){
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = session.find(Costumer.class, 1L);
        System.out.println(costumer);
        session.getTransaction().commit();
        session.close();
    }
    public static void findAndChange(){
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = session.find(Costumer.class, 1L);
        costumer.setName("Dupa");
        session.getTransaction().commit();
        session.close();
    }

}
