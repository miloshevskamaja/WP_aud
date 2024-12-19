package mk.ukim.finki.wp_aud1.service.impl;

import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.model.enumerations.Role;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wp_aud1.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp_aud1.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp_aud1.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp_aud1.repository.jpa.UserRepository;
import mk.ukim.finki.wp_aud1.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if(username == null || password==null || username.isEmpty() || password.isEmpty()){
            throw new InvalidArgumentException();
        }

        if(!password.equals(repeatPassword)){
            throw  new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        User user=new User(username,passwordEncoder.encode(password),name,surname,role);
        return this.userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }
}
