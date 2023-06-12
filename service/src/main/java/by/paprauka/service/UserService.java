package by.paprauka.service;

import by.paprauka.database.entity.UserEntity;
import by.paprauka.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> getBy(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }


    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
