package org.example.jvcarsharingservice.service.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.user.UpdateUserRequestDto;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.dto.user.registration.RegisterRequestDto;
import org.example.jvcarsharingservice.exception.RegistrationException;
import org.example.jvcarsharingservice.mapper.UserMapper;
import org.example.jvcarsharingservice.model.classes.Role;
import org.example.jvcarsharingservice.model.classes.User;
import org.example.jvcarsharingservice.model.enums.RoleName;
import org.example.jvcarsharingservice.repository.role.RoleRepository;
import org.example.jvcarsharingservice.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Role ROLE = new Role();
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDto register(RegisterRequestDto requestDto) {
        if (userRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with this email: "
                    + requestDto.getEmail() + " already exist");
        }
        User user = registerNewUser(requestDto);
        return userMapper.toDto(
                userRepository.save(user));
    }

    private User registerNewUser(RegisterRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        Role role = roleRepository.findByName(RoleName.CUSTOMER).orElseGet(
                () -> roleRepository.save(ROLE)
        );
        user.setRoles(Set.of(role));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        return user;
    }

    @Override
    public UserDto updateRole(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user with this id does not exist")
        );
        Role customer = roleRepository.findByName(RoleName.CUSTOMER).orElseGet(
                () -> roleRepository.save(ROLE)
        );
        Role manager = roleRepository.findByName(RoleName.MANAGER).orElseGet(
                () -> roleRepository.save(ROLE)
        );
        Role userRole = user.getRoles().stream().findFirst().orElseThrow(
                () -> new EntityNotFoundException("user with this id does not exist")
        );
        if (userRole.equals(RoleName.CUSTOMER)) {
            user.setRoles(Set.of(manager));
        } else {
            user.setRoles(Set.of(customer));
        }
        return userMapper.toDto(
                userRepository.save(user)
        );
    }

    @Override
    public UserDto getMyProfile(User user) {
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateMyProfile(User user, UpdateUserRequestDto requestDto) {
        user.setFirstName(requestDto.firstName());
        user.setLastName(requestDto.lastName());
        return userMapper.toDto(
                userRepository.save(user)
        );
    }
}
