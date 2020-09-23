package ru.developer.xoma.demotodolist.service;

import ru.developer.xoma.demotodolist.persist.repo.ToDoRepository;
import ru.developer.xoma.demotodolist.persist.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ru.developer.xoma.demotodolist.persist.entity.ToDo;
import ru.developer.xoma.demotodolist.repr.ToDoRepr;
import org.springframework.stereotype.Service;
import ru.developer.xoma.demotodolist.repr.UserRepr;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;

import static ru.developer.xoma.demotodolist.security.Utils.getCurrentUser;

@Service
@Transactional
public class ToDoService {

    private ToDoRepository toDoRepository;

    private UserRepository userRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    public Optional<ToDoRepr> findById(Long id) {
        return toDoRepository.findById(id).map(ToDoRepr::new);
    }

    public List<ToDoRepr> findToDosByUserId(Long userId) {
        return toDoRepository.findToDosByUserId(userId);
    }

    public List<UserRepr> findUsernameByUserId(Long userId) {
        return toDoRepository.findUsernameByUserId(userId);
    }

    public void save(ToDoRepr toDoRepr) {
        getCurrentUser()
                .flatMap(userRepository::getUserByUsername)
                .ifPresent(user -> {
                    ToDo toDo = new ToDo();
                    toDo.setId(toDoRepr.getId());
                    toDo.setUser(user);

                    toDo.setTask(toDoRepr.getTask());
                    toDo.setProject(toDoRepr.getProject());

                    toDo.setStartDate(toDoRepr.getStartDate());
                    toDo.setEndDate(toDoRepr.getEndDate());

                    toDoRepository.save(toDo);
                });
    }

    public void delete(Long id) {
        toDoRepository.findById(id)
                .ifPresent(toDo -> toDoRepository.delete(toDo));
    }
}
