package com.github.template.model.db.to.userDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String role;
}
