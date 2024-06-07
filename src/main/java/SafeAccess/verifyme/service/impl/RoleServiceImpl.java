package SafeAccess.verifyme.service.impl;

import SafeAccess.verifyme.domain.entity.Role;
import SafeAccess.verifyme.repository.RoleRepository;
import SafeAccess.verifyme.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return Optional.empty();
    }
}
