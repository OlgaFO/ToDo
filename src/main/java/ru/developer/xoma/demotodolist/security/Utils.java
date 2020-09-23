package ru.developer.xoma.demotodolist.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public class Utils {

    public static Optional<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of(authentication.getName());
        }

        return Optional.empty();
    }
}
