package tech.ignitr.habitus.service.auth;

import lombok.Builder;
import lombok.Data;
import tech.ignitr.habitus.data.users.User;

@Data
@Builder
public class AuthenticationResponse {

    private String token;
    private User user;
}
