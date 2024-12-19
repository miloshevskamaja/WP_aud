package mk.ukim.finki.wp_aud1.repository.jpa;

import mk.ukim.finki.wp_aud1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    void deleteByName(String name);
}
