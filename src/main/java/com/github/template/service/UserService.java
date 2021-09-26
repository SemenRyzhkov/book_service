package com.github.template.service;

import com.github.template.model.db.to.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> getAll(Pageable pageable);

    UserDto get(long id);

    void create(UserDto user);

    void update(UserDto user, long id);

    void delete(long id);
}