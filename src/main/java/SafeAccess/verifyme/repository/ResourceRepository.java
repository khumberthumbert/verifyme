package SafeAccess.verifyme.repository;

import SafeAccess.verifyme.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
