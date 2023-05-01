package tech.ignitr.habitus.web.user;

import java.util.UUID;

public class UserRequestModel {

    private UUID id;
    private String name;
    private String email;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
