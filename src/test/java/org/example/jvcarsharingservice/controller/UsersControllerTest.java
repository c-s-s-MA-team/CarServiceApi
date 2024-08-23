package org.example.jvcarsharingservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import org.example.jvcarsharingservice.dto.user.UpdateUserRequestDto;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.model.classes.User;
import org.example.jvcarsharingservice.model.enums.Role;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTest {
    public static final long ID = 1L;
    public static final String EMAIL = "admin@admin.com";
    public static final String FIRST_NAME = "B";
    public static final String LAST_NAME = "W";
    public static final String PASSWORD = "password";
    public static final Role ROLE = Role.MANAGER;
    private static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @AfterAll
    static void afterAll(@Autowired DataSource dataSource) {
        teardown(dataSource);
    }

    @SneakyThrows
    private static void teardown(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("db/controller/delete-from-users.sql")
            );
        }
    }

    @Test
    @WithMockUser(username = "user", authorities = {"MANAGER"})
    @DisplayName("Test updating user role successfully - MANAGER only")
    @Sql(scripts = "classpath:db/controller/add-to-users.sql")
    void updateUserRole_Success() throws Exception {
        //given
        UserDto userDto = getUserDto();
        Long id = userDto.getId();

        //when
        MvcResult result = mockMvc.perform(
                        put("/users/" + id + "/role")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn();

        //then
        UserDto actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                UserDto.class);

        EqualsBuilder.reflectionEquals(userDto, actual, "id");
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
