package com.github.template.mapper;

import com.github.template.model.db.db.User;
import com.github.template.model.db.to.userDto.AdminDto;
import com.github.template.model.db.to.userDto.UserDto;
import com.github.template.model.db.to.userDto.UserEditDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDto(User user);

    @InheritInverseConfiguration
    User dtoToEntity(UserDto dto);

    User dtoToEntity(UserEditDto editDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromEditDto(UserEditDto userEditDto, @MappingTarget User user);

    @BeanMapping(
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchFromAdminDto(AdminDto adminDto, @MappingTarget User user);

    List<UserDto> usersToUsersDto(List<User> users);

    @InheritInverseConfiguration
    List<User> usersDtoToUsers(List<UserDto> dtos);
}
