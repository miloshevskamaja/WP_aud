package mk.ukim.finki.wp_aud1.service.impl;

import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp_aud1.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp_aud1.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp_aud1.repository.jpa.UserRepository;
import mk.ukim.finki.wp_aud1.service.AuthService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.wp_aud1.repository.impl.InMemoryUserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
