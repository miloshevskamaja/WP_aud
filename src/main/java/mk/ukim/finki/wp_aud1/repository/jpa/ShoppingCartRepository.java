package mk.ukim.finki.wp_aud1.repository.jpa;

import mk.ukim.finki.wp_aud1.model.ShoppingCart;
import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
