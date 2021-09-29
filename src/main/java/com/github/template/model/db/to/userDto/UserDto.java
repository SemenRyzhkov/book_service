package com.github.template.model.db.to.userDto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String role;
}
