package ru.developer.xoma.demotodolist.repr;

import javax.validation.constraints.NotEmpty;

public class UserRepr {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;
    private int usergroup;

    public UserRepr() {
    }

    public UserRepr(@NotEmpty String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public int getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(int usergroup) {
        this.usergroup = usergroup;
    }

    @Override
    public String toString() {
        return "UserRepr{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", usergroup=" + usergroup +
                '}';
    }
}
