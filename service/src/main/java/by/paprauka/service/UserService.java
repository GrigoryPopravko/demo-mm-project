package by.paprauka.service;

import by.paprauka.database.dto.LoginDto;
import by.paprauka.database.dto.RegistrationDto;
import by.paprauka.database.entity.UserEntity;
import by.paprauka.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static by.paprauka.database.entity.enam.Role.USER;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserEntity> getBy(LoginDto login) {
        return userRepository.findByEmailAndPassword(login.email(), login.password());
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }


    public UserEntity save(UserEntity user) {
        user.setRole(USER);
        return userRepository.save(user);
    }

    public Optional<UserEntity> createUser(RegistrationDto registration) {
        return Optional.of(userRepository.save(UserEntity.builder()
                .email(registration.email())
                .password(passwordEncoder.encode(registration.password()))
                .role(USER)
                .build()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(userEntity -> User.builder()
                        .username(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .authorities(userEntity.getRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
