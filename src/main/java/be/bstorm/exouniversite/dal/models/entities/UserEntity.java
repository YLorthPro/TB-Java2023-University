package be.bstorm.exouniversite.dal.models.entities;

import be.bstorm.exouniversite.dal.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name ="\'User\'")
@Setter
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Getter
    protected Long id;
    @Column(nullable = false)
    @Getter
    protected String nom;
    @Column(nullable = false)
    @Getter
    protected String prenom;
    @Column(nullable = false, unique = true)
    @Getter
    protected String login;
    @Column(nullable = false)
    protected String password;
    @Enumerated(EnumType.STRING)
    @Getter
    protected UserRole userRole;

    @ManyToMany
    @JoinTable(name = "user_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    protected Set<AddressEntity> addressEntities = new LinkedHashSet<>();

    public Set<AddressEntity> getAddress() {
        return new HashSet<>(addressEntities);
    }

    public void setAddress(Set<AddressEntity> address) {
        this.addressEntities.clear();
        this.addressEntities = addressEntities;
    }
    
    public abstract void setUserRole();
}
