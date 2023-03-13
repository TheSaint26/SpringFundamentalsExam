package bg.softuni.exam.model.session;

import bg.softuni.exam.model.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedInUser {
    private Long id;
    private String username;

    public LoggedInUser() {
    }

    public Long getId() {
        return id;
    }

    public LoggedInUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedInUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public void login(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }

    public boolean isLoggedIn() {
        return this.id != null;
    }

    public boolean isNotLoggedIn() {
        return !isLoggedIn();
    }
}
