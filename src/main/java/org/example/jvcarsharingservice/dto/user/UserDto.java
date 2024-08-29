package org.example.jvcarsharingservice.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
