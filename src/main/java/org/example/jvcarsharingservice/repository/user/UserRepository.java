package org.example.jvcarsharingservice.repository.user;

import java.util.Optional;
import org.example.jvcarsharingservice.model.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u "
            + "WHERE  u.email = :email")
    Optional<User> findUserByEmail(String email);
}
