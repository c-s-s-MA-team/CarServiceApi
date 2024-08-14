package org.example.jvcarsharingservice.servece.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.user.UpdateUserRequestDto;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.dto.user.login.LoginRequestDto;
import org.example.jvcarsharingservice.dto.user.registration.RegisterRequestDto;
import org.example.jvcarsharingservice.mapper.UserMapper;
import org.example.jvcarsharingservice.model.classes.User;
import org.example.jvcarsharingservice.model.enums.Role;
import org.example.jvcarsharingservice.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user with this id does not exist")
        );
        switch (user.getRole()) {
            case CUSTOMER -> user.setRole(Role.MANAGER);
            default -> user.setRole(Role.CUSTOMER);
        }
        return userMapper.toDto(
                userRepository.save(user)
        );
    }

    @Override
    public UserDto getMyProfile(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("user with this email does not exist")
        );
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateMyProfile(String email, UpdateUserRequestDto requestDto) {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("user with this email does not exist")
        );
        user.setEmail(email);
        return userMapper.toDto(
                userRepository.save(user)
        );
    }
}
