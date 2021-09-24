package com.github.template.service;

import com.github.template.mapper.UserMapper;
import com.github.template.model.db.db.User;
import com.github.template.model.db.to.UserDto;
import com.github.template.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
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
    public void create(@NonNull UserDto userDto) {
        repository.save(mapper.dtoToEntity(userDto));
    }

    @Override
    public void update(@NonNull UserDto userDto, long id) {
        repository.findById(id)
                .map(user -> {
                    userDto.setId(id);
                    return repository.save(mapper.dtoToEntity(userDto));
                })
                .orElseGet(() -> {
                    User user = mapper.dtoToEntity(userDto);
                    user.setId(id);
                    return repository.save(user);
                });
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
