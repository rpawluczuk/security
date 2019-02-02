package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AddressRespositoryHibernate implements AddressRepository {
    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public Address findById(Long id) {
        return null;
    }

    @Override
    public void save(Address address) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(address);
            transaction.commit();
            transaction = null;
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Address address) {

    }

    @Override
    public void delete(Address address) {

    }
}
