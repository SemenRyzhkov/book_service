package com.github.template.model.db.to;

import com.github.template.model.db.db.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private Role role;
}
