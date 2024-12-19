package mk.ukim.finki.wp_aud1.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_aud1.model.*;
import mk.ukim.finki.wp_aud1.model.enumerations.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Category> categories=new ArrayList<>();
    public static List<User> users=new ArrayList<>();
    public static List<Manufacturer> manufacturers=new ArrayList<>();
    public static List<Product> products=new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;

    public DataHolder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init(){
        categories.add(new Category("Books", "Books category"));
        categories.add(new Category("Movies","Movies category"));

        users.add(new User("maja.miloshevska", passwordEncoder.encode("mm"),"Maja","Miloshevska", Role.ROLE_USER));
        users.add(new User("admin", passwordEncoder.encode("admin"),"admin","admin",Role.ROLE_ADMIN));

        Manufacturer manufacturer=new Manufacturer("Nike","NY NY");
        manufacturers.add(manufacturer);

        Category category=new Category("Sport","Sport category");
        categories.add(category);

        products.add(new Product("Ball 1", 235.8,7,category,manufacturer));
        products.add(new Product("Ball 2", 235.8,7,category,manufacturer));
        products.add(new Product("Ball 3", 235.8,7,category,manufacturer));
    }
}
