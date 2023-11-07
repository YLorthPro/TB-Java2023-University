package be.bstorm.exouniversite.dal.models.entities;

import be.bstorm.exouniversite.dal.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name ="Utilisateur")
public abstract class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Getter
    @Setter
    protected Long id;
    @Column(nullable = false)
    @Getter
    @Setter
    protected String nom;
    @Column(nullable = false)
    @Getter
    @Setter
    protected String prenom;
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    protected String login;
    @Column(nullable = false)
    @Setter
    protected String password;
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    protected Set<UserRole> userRoles;

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
        this.addressEntities = address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
