package com.github.template.service;

import com.github.template.mapper.UserMapper;
import com.github.template.model.db.db.Role;
import com.github.template.model.db.db.Status;
import com.github.template.model.db.db.User;
import com.github.template.model.db.to.userDto.AdminDto;
import com.github.template.model.db.to.userDto.UserDto;
import com.github.template.model.db.to.userDto.UserEditDto;
import com.github.template.repository.UserRepository;
import com.github.template.security.SecurityUser;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("userServiceImpl")
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    public UserDto get(long id) {
        return mapper.entityToDto(repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with id %d does not exist", id))));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with email %s does not exist", email)));
        return SecurityUser.fromUser(user);
    }

    @Override
    public UserDto create(@NonNull UserEditDto userDto) {
        User newUser = mapper.dtoToEntity(userDto);
        newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        newUser.setRole(Role.USER);
        newUser.setStatus(Status.ACTIVE);
        User savedUser;
        try {
            savedUser = repository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Email already exist");
        }
        return mapper.entityToDto(savedUser);
    }

    @Override
    public UserDto update(@NonNull UserEditDto userDto, long id) {
        User user = repository.getOne(id);
        mapper.patchFromEditDto(userDto, user);
        User updatedUser;
        try {
            updatedUser = repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Email already exist");
        }
        return mapper.entityToDto(updatedUser);
    }

    @Override
    public UserDto updateForAdmin(AdminDto dto, long id) {
        User user = repository.getOne(id);
        mapper.patchFromAdminDto(dto, user);
        return mapper.entityToDto(repository.save(user));
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
