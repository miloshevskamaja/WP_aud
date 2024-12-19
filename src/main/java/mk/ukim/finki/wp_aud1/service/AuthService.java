package mk.ukim.finki.wp_aud1.service;
import mk.ukim.finki.wp_aud1.model.User;
public interface AuthService {
    User login(String username, String password);


}
