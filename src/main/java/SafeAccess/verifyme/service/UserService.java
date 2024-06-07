package SafeAccess.verifyme.service;

import SafeAccess.verifyme.domain.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user, String roleName);
    Optional<User> findByUsername(String username);
}
