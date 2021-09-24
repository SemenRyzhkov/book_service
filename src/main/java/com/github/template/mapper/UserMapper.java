package com.github.template.mapper;

import com.github.template.model.db.db.User;
import com.github.template.model.db.to.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDto(User user);

    @InheritInverseConfiguration
    User dtoToEntity(UserDto dto);

    List<UserDto> usersToUsersDto(List<User>users);

    @InheritInverseConfiguration
    List<User> usersDtoToUsers(List<UserDto> dtos);
}
