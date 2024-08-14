package org.example.jvcarsharingservice.servece.user;

import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.dto.user.login.LoginRequestDto;
import org.example.jvcarsharingservice.dto.user.registration.RegisterRequestDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserDto register(RegisterRequestDto requestDto) {
        return null;
    }

    @Override
    public UserDto login(LoginRequestDto requestDto) {
        return null;
    }

    @Override
    public UserDto updateRole(Long id) {
        return null;
    }

    @Override
    public UserDto getMyProfile(String email) {
        return null;
    }

    @Override
    public UserDto updateMyProfile(String email) {
        return null;
    }
}
