package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();
    }
}
