package com.github.template.service;

import com.github.template.model.db.to.userDto.AdminDto;
import com.github.template.model.db.to.userDto.UserDto;
import com.github.template.model.db.to.userDto.UserEditDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    Page<UserDto> getAll(Pageable pageable);

    UserDto get(long id);

    UserDetails loadUserByUsername(String email);

    UserDto create(UserEditDto user);

    UserDto update(UserEditDto user, long id);

    UserDto updateForAdmin(AdminDto dto, long id);

    void delete(long id);
}
