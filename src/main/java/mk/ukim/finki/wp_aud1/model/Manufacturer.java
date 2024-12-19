package mk.ukim.finki.wp_aud1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    @Column(name="manufacturer_address")
    private String address;

    public Manufacturer() {
    }

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
