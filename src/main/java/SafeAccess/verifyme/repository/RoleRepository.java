package SafeAccess.verifyme.repository;

import SafeAccess.verifyme.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 1. JpaRepository를 상속받는 이유 : 기본적인 작업들을 제공하는 기능들 사용하기 위함.
 * 2. <User, Long> : 리포지토리가 어떤 엔티티 타입과 그 엔티티의 ID 타입을 처리하는지 명확히 하기 위함.
 * User: 리포지토리가 처리할 엔티티 타입, Long: 엔티티의 ID 타입. User엔티티의 ID가 'Long'타입이기 때문에 이를 지정한다.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * User 엔티티를 조회하기 위함. 조회결과가 없을 수 있기 떄문에 Optional 처리.
     */
    Optional<Role> findByName(String name);
}
