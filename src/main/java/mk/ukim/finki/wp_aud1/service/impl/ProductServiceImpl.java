package mk.ukim.finki.wp_aud1.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp_aud1.model.Category;
import mk.ukim.finki.wp_aud1.model.Manufacturer;
import mk.ukim.finki.wp_aud1.model.Product;
import mk.ukim.finki.wp_aud1.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp_aud1.model.exceptions.ManufactorerNotFoundException;
import mk.ukim.finki.wp_aud1.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.wp_aud1.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.wp_aud1.repository.impl.InMemoryProductRepository;
import mk.ukim.finki.wp_aud1.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp_aud1.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.wp_aud1.repository.jpa.ProductRepository;
import mk.ukim.finki.wp_aud1.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository=manufacturerRepository;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category=this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException(categoryId));

        Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()->new ManufactorerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);

        return Optional.of(this.productRepository.save(new Product(name,price,quantity,category,manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
