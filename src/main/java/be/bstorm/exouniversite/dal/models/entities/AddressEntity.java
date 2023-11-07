package be.bstorm.exouniversite.dal.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="Address")
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    @Getter
    private Long id;
    @Column(nullable = false)
    @Getter
    private String numero;
    @Column(nullable = false)
    @Getter
    private String rue;
    @Column(nullable = false)
    @Getter
    private String codePostal;
    @Column(nullable = false)
    @Getter
    private String ville;
}
