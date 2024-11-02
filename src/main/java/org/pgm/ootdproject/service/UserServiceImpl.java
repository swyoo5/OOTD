package org.pgm.ootdproject.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setNickname(updatedUser.getNickname());
        user.setIntroduce(updatedUser.getIntroduce());
        user.setProfileImage(updatedUser.getProfileImage());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> readUser(Long userId) {
        return userRepository.findById(userId);
    }
}
