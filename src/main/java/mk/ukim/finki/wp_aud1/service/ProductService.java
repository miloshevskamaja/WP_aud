package mk.ukim.finki.wp_aud1.service;

import mk.ukim.finki.wp_aud1.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long Id);
    Optional<Product> findByName(String name);
    Optional<Product> save(String name, Double price, Integer quantity,
                           Long categoryId, Long manufacturerId);
    void deleteById(Long id);
}
