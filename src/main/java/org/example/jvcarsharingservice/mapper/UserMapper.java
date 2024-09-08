package org.example.jvcarsharingservice.mapper;

import org.example.jvcarsharingservice.config.MapperConfig;
import org.example.jvcarsharingservice.dto.user.UpdateUserRequestDto;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.dto.user.registration.RegisterRequestDto;
import org.example.jvcarsharingservice.model.classes.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UpdateUserRequestDto userDto);

    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    User toEntity(RegisterRequestDto requestDto);
}
