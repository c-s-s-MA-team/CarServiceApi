package org.example.jvcarsharingservice.servece.user;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.example.jvcarsharingservice.dto.user.UpdateUserRequestDto;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.mapper.UserMapper;
import org.example.jvcarsharingservice.model.classes.User;
import org.example.jvcarsharingservice.model.enums.Role;
import org.example.jvcarsharingservice.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    public static final long ID = 1L;
    public static final String EMAIL = "admin@admin.com";
    public static final String FIRST_NAME = "B";
    public static final String LAST_NAME = "W";
    public static final String PASSWORD = "password";
    public static final Role ROLE = Role.MANAGER;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("updateRole_withValidId_returnsUpdatedUserDto")
    void updateRole_withValidId_returnsUpdatedUserDto() {
        // Given
        Long userId = 1L;
        User user = getUser();
        UserDto userDto = getUserDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        // When
        UserDto result = userService.updateRole(userId);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDto, result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toDto(user);
        verifyNoMoreInteractions(userRepository, userMapper);
    }

    @Test
    @DisplayName("updateRole_withInvalidId_throwsEntityNotFoundException")
    void updateRole_withInvalidId_throwsEntityNotFoundException() {
        // Given

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException entityNotFoundException =
                Assertions.assertThrows(
                        EntityNotFoundException.class, () -> userService.updateRole(anyLong()));
        Assertions.assertEquals("user with this id does not exist",
                entityNotFoundException.getMessage());
        verify(userRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("getMyProfile_withValidUser_returnsUserDto")
    void getMyProfile_withValidUser_returnsUserDto() {
        // Given
        User user = getUser();
        UserDto userDto = getUserDto();

        when(userMapper.toDto(user)).thenReturn(userDto);

        // When
        UserDto result = userService.getMyProfile(user);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDto, result);
        verify(userMapper, times(1)).toDto(user);
        verifyNoMoreInteractions(userMapper);
    }

    @Test
    @DisplayName("updateMyProfile_withValidInput_returnsUpdatedUserDto")
    void updateMyProfile_withValidInput_returnsUpdatedUserDto() {
        // Given
        User user = getUser();
        UpdateUserRequestDto requestDto = getUpdateUserRequestDto();
        UserDto userDto = getUserDto();

        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        // When
        UserDto result = userService.updateMyProfile(user, requestDto);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDto, result);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toDto(user);
        verifyNoMoreInteractions(userRepository, userMapper);
    }

    private UpdateUserRequestDto getUpdateUserRequestDto() {
        return new UpdateUserRequestDto(
                FIRST_NAME, LAST_NAME
        );
    }

    private User getUser() {
        User user = new User();
        user.setId(ID);
        user.setEmail(EMAIL);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        user.setDeleted(false);
        return user;
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(ID);
        userDto.setEmail(EMAIL);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setRole(String.valueOf(ROLE));
        return userDto;
    }
}
