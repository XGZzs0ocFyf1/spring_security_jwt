package service;

import entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }
}
