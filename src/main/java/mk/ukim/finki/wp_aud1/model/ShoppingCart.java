package mk.ukim.finki.wp_aud1.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.wp_aud1.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.id = (long) (Math.random()*1000);
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
