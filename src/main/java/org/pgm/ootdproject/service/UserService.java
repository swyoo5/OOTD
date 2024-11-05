package org.pgm.ootdproject.service;

import org.pgm.ootdproject.DTO.UserDTO;
import org.pgm.ootdproject.entity.User;

import java.util.Optional;

// 마이페이지에서 사용자 수정, 조회, 탈퇴
public interface UserService {
    UserDTO updateUser(Long userId, UserDTO updatedUserDTO);
    void deleteUser(Long userId);
    public Optional<UserDTO> readUser(Long userId);
}
