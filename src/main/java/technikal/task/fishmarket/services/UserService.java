package technikal.task.fishmarket.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import technikal.task.fishmarket.models.Role;
import technikal.task.fishmarket.models.User;
import technikal.task.fishmarket.repo.UserRepository;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user){
        if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
            user.setRoles(Collections.singleton(Role.ADMIN));
        }

        User encodedUser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getEmail(), user.getPhone(), user.getRoles());

        userRepository.save(encodedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }
}