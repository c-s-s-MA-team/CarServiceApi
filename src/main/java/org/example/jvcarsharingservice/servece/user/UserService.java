package org.example.jvcarsharingservice.servece.user;

import org.example.jvcarsharingservice.dto.user.UpdateUserRequestDto;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.dto.user.login.LoginRequestDto;
import org.example.jvcarsharingservice.dto.user.registration.RegisterRequestDto;

public interface UserService {
    UserDto register(RegisterRequestDto requestDto);

    UserDto login(LoginRequestDto requestDto);

    UserDto updateRole(Long id);

    UserDto getMyProfile(String email);

    UserDto updateMyProfile(String email, UpdateUserRequestDto requestDto);
}
