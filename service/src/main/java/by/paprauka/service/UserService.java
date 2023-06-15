package by.paprauka.service;

import by.paprauka.database.dto.LoginDto;
import by.paprauka.database.entity.UserEntity;
import by.paprauka.database.entity.enam.Role;
import by.paprauka.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    public Optional<UserEntity> getBy(LoginDto login) {
        return userRepository.findByEmailAndPassword(login.email(), login.password());
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }


    public UserEntity save(UserEntity user) {
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}
