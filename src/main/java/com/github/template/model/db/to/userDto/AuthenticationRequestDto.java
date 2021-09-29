package com.github.template.model.db.to.userDto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String email;

    private String password;
}
