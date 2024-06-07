package SafeAccess.verifyme;

import SafeAccess.verifyme.domain.entity.Role;
import SafeAccess.verifyme.domain.entity.User;
import SafeAccess.verifyme.domain.entity.UserRole;
import SafeAccess.verifyme.repository.RoleRepository;
import SafeAccess.verifyme.repository.UserRepository;
import SafeAccess.verifyme.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository, roleRepository, userRoleRepository, passwordEncoder);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@example.com");

        Role role = new Role();
        role.setName("ROLE_USER");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRoleRepository.save(any(UserRole.class))).thenReturn(new UserRole());

        User registeredUser = userService.registerUser(user, "ROLE_USER");

        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
        verify(userRepository, times(1)). save(user);
        verify(userRoleRepository, times(1)).save(any(UserRole.class));
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUsername("testuser");

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
        verify(userRepository, times(1)).findByUsername("testuser");
    }
}
