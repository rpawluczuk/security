package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = SessionManager.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.close();

        selectByCriteria();
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


    public static void deleteQuery() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session
                .createQuery("delete Costumer where id in (:idsForDelete)");
        query.setParameter("idsForDelete", Arrays.asList(2L, 5L));
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void selectByCriteria() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
// select costumerRoot
// from Costumer costumerRoot
// where costumerRoot.name = Przemio
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        //typ zwracany
        CriteriaQuery<Costumer> criteriaQuery = criteriaBuilder
                .createQuery(Costumer.class);
        //inicjalizacja from
        Root<Costumer> costumerRoot = criteriaQuery.from(Costumer.class);
        //blok where
        criteriaQuery.where(
                criteriaBuilder.equal(costumerRoot.get("name"), "Przemio")
        );
        Query<Costumer> query = session.createQuery(criteriaQuery);
        List<Costumer> list = query.list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }


}
