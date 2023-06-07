package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User register(String username, String name, String surname, String password, String repeatPassword, LocalDate dateOfBirth, Role role);
    List<User> findAll();
    Optional<User> findByUsername(String username);
}
