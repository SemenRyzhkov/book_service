package com.github.template.controller;

import com.github.template.model.db.to.userDto.AdminDto;
import com.github.template.model.db.to.userDto.UserDto;
import com.github.template.model.db.to.userDto.UserEditDto;
import com.github.template.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = UserController.REST_URL)
@Slf4j
public class UserController {
    static final String REST_URL = "/api/users";

    private final UserService service;

    @GetMapping
    @PreAuthorize("hasAuthority('users:write')")
    public Page<UserDto> getAll(
            @PageableDefault Pageable pageable) {
        log.info("getAllUser");
        return service.getAll(pageable);
    }

    @GetMapping("{id}")
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public UserDto get(@PathVariable long id,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("get user {}", id);
        return service.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto create(@RequestBody @Valid UserEditDto userDto) {
        log.info("create user {}", userDto);
        return service.create(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/admin-edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('users:write')")
    public UserDto updateForAdmin(@RequestBody @Valid AdminDto adminDto, @PathVariable long id) {
        log.info("update user {}", adminDto);
        return service.updateForAdmin(adminDto, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user-edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    public UserDto update(@RequestBody @Valid UserEditDto userDto, @PathVariable long id,
                          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("update user {}", userDto);
        return service.update(userDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id.equals(#usernamePasswordAuthenticationToken.principal.id)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id,
                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        log.info("delete user{}", id);
        service.delete(id);
    }
}

