package service;

import entities.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repositories.RoleRepository;
import repositories.UserRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public void createNewUser(User user) throws RoleNotFoundException {
        var fromDb = userRepository.findByUsername(user.getUsername());
        if (!fromDb.isEmpty()) {
            throw new RuntimeException(
                    String.format("Пользователь с именем '%s' уже существует", user.getUsername())
            );
        }
        String roleUser = "ROLE_USER";
        var userRole = roleService.findByName(roleUser)
                .orElseThrow( () ->  new RoleNotFoundException(
                        String.format("Роль '%s' не найдена", roleUser)
                ));
        user.setRoles(List.of(userRole));
        userRepository.save(user);
    }
}
