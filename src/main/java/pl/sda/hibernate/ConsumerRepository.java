package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class ConsumerRepository {


    public void createCustomerWithAddress1() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = new Costumer();
        costumer.setName("Franek Bąbka");
        Address address = new Address();
        address.setCountry("Polska");
        address.setStreet("Wiejska");
        address.setHouseNumber(23);
        costumer.setAddress(address);
        session.save(costumer);
        System.out.println(costumer.getAddress());
        session.getTransaction().commit();
        session.close();
    }

    public void createCustomerWithAddress2() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = new Costumer();
        costumer.setName("Franek Bąbka");
        Address address = session.find(Address.class, 2L);
        costumer.setAddress(address);
        session.save(costumer);
        System.out.println(costumer.getAddress());
        session.getTransaction().commit();
        session.close();
    }

    public void createCustomerWithAddress3() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = new Costumer();
        costumer.setName("Franek Bąbka");
        Address address = new Address();
        address.setId(2L);
        costumer.setAddress(address);
        session.save(costumer);
        System.out.println(costumer.getAddress());
        session.getTransaction().commit();
        session.close();
    }

    public void findCustomerWithAddress() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = session.find(Costumer.class, 2L);
        System.out.println("pobieram klienta");
        System.out.println(costumer);
        System.out.println("odczytuje adres klienta");
        System.out.println(costumer.getAddress());
        session.getTransaction().commit();
        session.close();
    }

    public void findCustomerByCityName(String cityName) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Costumer> query = session.createQuery("select c " +
                            "from Costumer c " +
                            "where c.address.city = :cityName",
                    Costumer.class);
            query.setParameter("cityName", cityName);
            for (Costumer costumer : query.list()) {
                System.out.println(costumer);
            }
            transaction.commit();
            transaction = null;
        }finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
