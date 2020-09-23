package ru.developer.xoma.demotodolist.repr;

import org.springframework.format.annotation.DateTimeFormat;
import ru.developer.xoma.demotodolist.persist.entity.ToDo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ToDoRepr {

    private Long id;
    private String username;

    @NotEmpty
    private String task;

    @NotEmpty
    private String project;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public ToDoRepr() {
    }

    public ToDoRepr(Long id, String username, @NotEmpty String task, @NotEmpty String project, @NotNull LocalDate startDate, @NotNull LocalDate endDate) {
        this.id = id;
        this.username = username;

        this.task = task;
        this.project = project;

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ToDoRepr(ToDo toDo) {
        this.id = toDo.getId();
        this.username = toDo.getUser().getUsername();

        this.task = toDo.getTask();
        this.project = toDo.getProject();

        this.startDate = toDo.getStartDate();
        this.endDate = toDo.getEndDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
