package ru.developer.xoma.demotodolist.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.developer.xoma.demotodolist.persist.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username).map(user -> new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")))).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
