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
        return convertEntityToDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<UserDTO> readUser(Long userId) {
        return userRepository.findById(userId).map(this::convertEntityToDTO);
    }

    @Override
    public void savdUser(UserDTO userDTO) {
        User user = convertDTOToEntity(userDTO);
        userRepository.save(user);
    }

    private User convertDTOToEntity(UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserId())
                .loginId(userDTO.getLoginId())
                .email(userDTO.getEmail())
                .regDate(userDTO.getRegDate())
                .profileImage(userDTO.getProfileImage())
                .nickname(userDTO.getNickname())
                .introduce(userDTO.getIntroduce())
                .build();
    }

    private UserDTO convertEntityToDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .regDate(user.getRegDate())
                .profileImage(user.getProfileImage())
                .nickname(user.getNickname())
                .introduce(user.getIntroduce())
                .build();
    }
}
