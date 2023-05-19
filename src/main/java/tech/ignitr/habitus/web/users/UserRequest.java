package tech.ignitr.habitus.web.users;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserRequest {

    private UUID id;
    private String name;
    private String email;
    private String password;

}
