package com.github.template.model.db.to;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
