package SafeAccess.verifyme.service.impl;

import SafeAccess.verifyme.domain.entity.Role;
import SafeAccess.verifyme.domain.entity.User;
import SafeAccess.verifyme.domain.entity.UserRole;
import SafeAccess.verifyme.repository.RoleRepository;
import SafeAccess.verifyme.repository.UserRepository;
import SafeAccess.verifyme.repository.UserRoleRepository;
import SafeAccess.verifyme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user, String roleName) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);

        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
