package com.github.template.service;

import com.github.template.mapper.UserMapper;
import com.github.template.model.db.db.User;
import com.github.template.model.db.to.UserDto;
import com.github.template.repository.UserRepository;
import com.github.template.security.SecurityUser;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDto);
    }

    @Override
    public UserDto get(long id) {
        return mapper.entityToDto(repository.getOne(id));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("user does not exist"));
        return SecurityUser.fromUser(user);
    }

    @Override
    public void create(@NonNull UserDto userDto) {

        repository.save(mapper.dtoToEntity(userDto));
    }

    @Override
    public void update(@NonNull UserDto userDto, long id) {
        repository.getOne(id);
        repository.save(mapper.dtoToEntity(userDto));
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
