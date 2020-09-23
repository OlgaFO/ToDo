package ru.developer.xoma.demotodolist.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.developer.xoma.demotodolist.persist.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ru.developer.xoma.demotodolist.persist.entity.User;
import ru.developer.xoma.demotodolist.repr.UserRepr;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static ru.developer.xoma.demotodolist.security.Utils.getCurrentUser;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void create(UserRepr userRepr) {
        User user = new User();

        user.setUsername(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        user.setUsergroup(userRepr.getUsergroup());

        userRepository.save(user);
    }

    public Optional<Long> getCurrentUserId() {
        return getCurrentUser()
                .flatMap(userRepository::getUserByUsername)
                .map(User::getId);
    }
}
