package org.example.jvcarsharingservice.repository.user;

import org.example.jvcarsharingservice.model.classes.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("""
            """)
    @Sql(scripts = {"classpath:db/repo/clean-user-repo.sql",
            "classpath:db/repo/add-to-user-repo.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:db/repo/clean-user-repo.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findUserByEmail_getOptionalOfUserFromEmail() {
        String email = "admin@admin.com";
        User testUser = getUser();

        User user = userRepository.findUserByEmail(email).orElse(null);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(user),
                () -> Assertions.assertEquals(email, user.getEmail()),
                () -> Assertions.assertEquals(testUser.getId(), user.getId()),
                () -> Assertions.assertEquals(testUser.getEmail(), user.getEmail()),
                () -> Assertions.assertEquals(testUser.getFirstName(), user.getFirstName()),
                () -> Assertions.assertEquals(testUser.getLastName(), user.getLastName())
        );
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("B");
        user.setLastName("C");
        user.setPassword("password");
        user.setEmail("admin@admin.com");
        user.setDeleted(false);
        return user;
    }
}
