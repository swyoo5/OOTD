package org.pgm.ootdproject.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.DTO.UserDTO;
import org.pgm.ootdproject.entity.User;
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
    public UserDTO updateUser(Long userId, UserDTO updatedUserDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setNickname(updatedUserDTO.getNickname());
        user.setIntroduce(updatedUserDTO.getIntroduce());
        user.setProfileImage(updatedUserDTO.getProfileImage());
        user.setEmail(updatedUserDTO.getEmail());
        userRepository.save(user);
        return convertToDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<UserDTO> readUser(Long userId) {
        return userRepository.findById(userId).map(this::convertToDTO);
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .nickname(user.getNickname())
                .introduce(user.getIntroduce())
                .build();
    }
}
