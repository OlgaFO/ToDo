package ru.developer.xoma.demotodolist.persist.repo;

import org.springframework.data.repository.CrudRepository;
import ru.developer.xoma.demotodolist.persist.entity.ToDo;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import ru.developer.xoma.demotodolist.repr.ToDoRepr;
import org.springframework.stereotype.Repository;
import ru.developer.xoma.demotodolist.repr.UserRepr;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    @Query("select new ru.developer.xoma.demotodolist.repr.ToDoRepr(t.id, t.user.username, t.task, t.project, t.startDate, t.endDate) " +
            " from ToDo t " +
            "where t.user.id = :userId")
    List<ToDoRepr> findToDosByUserId(@Param("userId") Long userId);

    @Query("select new ru.developer.xoma.demotodolist.repr.UserRepr(u.username) " +
            " from User u " +
            "where u.id = :userId")
    List<UserRepr> findUsernameByUserId(@Param("userId") Long userId);
}
