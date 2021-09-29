package com.github.template.security;


import com.github.template.model.db.db.Status;
import com.github.template.model.db.db.User;

public class SecurityUser {
    public static UserDetailsImpl fromUser(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(), user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getAuthorities()
        );
    }
}
