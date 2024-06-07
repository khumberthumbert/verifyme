package SafeAccess.verifyme;


import SafeAccess.verifyme.domain.entity.Role;
import SafeAccess.verifyme.domain.entity.User;
import SafeAccess.verifyme.domain.entity.UserRole;
import SafeAccess.verifyme.repository.RoleRepository;
import SafeAccess.verifyme.repository.UserRepository;
import SafeAccess.verifyme.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 권한 만들기
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        //유저 만들기 + 권한 유저 만들기
        User user = new User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEmail("testuser@example.com");
        user.setEnabled(true);
        userRepository.save(user);

        UserRole adminRoleAssignment = new UserRole();
        adminRoleAssignment.setUser(user);
        adminRoleAssignment.setRole(adminRole);
        userRoleRepository.save(adminRoleAssignment);
    }
}
