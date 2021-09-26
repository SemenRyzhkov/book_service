package com.github.template.controller;

import com.github.template.model.db.to.UserDto;
import com.github.template.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = UserController.REST_URL)
@Slf4j
public class UserController {
    static final String REST_URL = "/api/users";

    private final UserService service;

    @GetMapping
    public Page<UserDto> getAll(
            @PageableDefault Pageable pageable){
        log.info("getAllUser");
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public UserDto get(@PathVariable long id){
        log.info("get user {}", id);
        return service.get(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody UserDto userDto){
        log.info("create user{}", userDto);
        service.create(userDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserDto userDto, @PathVariable long id){
        log.info("update user{}", userDto);
        service.update(userDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id){
        log.info("delete user{}", id);
        service.delete(id);
    }
}

