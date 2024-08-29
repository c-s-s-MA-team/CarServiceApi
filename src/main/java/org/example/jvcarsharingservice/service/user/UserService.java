package org.example.jvcarsharingservice.service.user;

import org.example.jvcarsharingservice.dto.user.UpdateUserRequestDto;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.dto.user.registration.RegisterRequestDto;
import org.example.jvcarsharingservice.model.classes.User;

public interface UserService {
    UserDto register(RegisterRequestDto requestDto);

    UserDto updateRole(Long id);

    UserDto getMyProfile(User user);

    UserDto updateMyProfile(User user, UpdateUserRequestDto requestDto);
}
