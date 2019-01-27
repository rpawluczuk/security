package pl.sda.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customersTable")
@NamedQueries({
        @NamedQuery(name = "selectByName",
                query = "select c from Costumer c where c.name = :value")
})
public class Costumer {
    @Id
    @Column(name = "primary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costumer costumer = (Costumer) o;
        return Objects.equals(id, costumer.id) &&
                Objects.equals(name, costumer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
