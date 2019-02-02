package pl.sda.hibernate;

import java.util.List;

public interface AddressRepository {
    List<Address> findAll();
    Address findById(Long id);
    void save(Address address);
    void update(Address address);
    void delete(Address address);
}
