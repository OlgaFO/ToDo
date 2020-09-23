package ru.developer.xoma.demotodolist.persist.repo;

import org.springframework.data.repository.CrudRepository;
import ru.developer.xoma.demotodolist.persist.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUsergroupByUsername(String username);
}
