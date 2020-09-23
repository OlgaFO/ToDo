package ru.developer.xoma.demotodolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.developer.xoma.demotodolist.persist.entity.User;
import ru.developer.xoma.demotodolist.service.ToDoService;
import ru.developer.xoma.demotodolist.service.UserService;
import org.springframework.validation.BindingResult;
import ru.developer.xoma.demotodolist.repr.ToDoRepr;
import ru.developer.xoma.demotodolist.repr.UserRepr;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.Optional;
import java.util.List;

@Controller
public class TodoController {

    private ToDoService toDoService;

    private UserService userService;

    @Autowired
    public TodoController(ToDoService toDoService, UserService userService) {
        this.toDoService = toDoService;
        this.userService = userService;
    }

    @GetMapping("")
    public String indexPage(Model model) {
        return "redirect:/todo/all";
    }

    @GetMapping("/todo/all")
    public String allTodosPage(Model model) {
        List<ToDoRepr> todos = toDoService.findToDosByUserId(userService.getCurrentUserId().orElseThrow(ResourceNotFoundException::new));

        model.addAttribute("todos", todos);

        List<UserRepr> l = toDoService.findUsernameByUserId(userService.getCurrentUserId().orElseThrow(ResourceNotFoundException::new));
        Optional<User> i = userService.getUserRepository().getUsergroupByUsername(l.get(0).getUsername());

        if(i.get().getUsergroup() == 1) {
            return "index_yellow";
        }
        else {
            return "index_green";
        }
    }

    @GetMapping("/todo/{id}")
    public String todoPage(@PathVariable("id") Long id, Model model) {
        ToDoRepr toDoRepr = toDoService.findById(id).orElseThrow(ResourceNotFoundException::new);

        model.addAttribute("todo", toDoRepr);

        return "todo";
    }

    @GetMapping("/todo/create")
    public String createTodoPage(Model model) {
        model.addAttribute("todo", new ToDoRepr());
        return "todo";
    }

    @PostMapping("/todo/create")
    public String createTodoPost(@ModelAttribute("todo") @Valid ToDoRepr toDoRepr, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        toDoService.save(toDoRepr);

        return "redirect:/";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        toDoService.delete(id);
        return "redirect:/";
    }
}
