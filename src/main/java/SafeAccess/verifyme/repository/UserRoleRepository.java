package SafeAccess.verifyme.repository;

import SafeAccess.verifyme.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
