package security.root.service;

import security.root.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import security.root.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }
}
