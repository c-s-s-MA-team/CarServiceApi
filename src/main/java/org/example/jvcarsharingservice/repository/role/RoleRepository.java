package org.example.jvcarsharingservice.repository.role;

import java.util.Optional;
import org.example.jvcarsharingservice.model.classes.Role;
import org.example.jvcarsharingservice.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role, Long>,
        JpaSpecificationExecutor<Role> {
    Optional<Role> findByName(RoleName name);
}
