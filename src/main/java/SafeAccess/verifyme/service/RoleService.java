package SafeAccess.verifyme.service;

import SafeAccess.verifyme.domain.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
}
