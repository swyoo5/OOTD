package org.pgm.ootdproject.service;

import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.entity.User;
import org.pgm.ootdproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

// 마이페이지에서 사용자 수정, 조회, 탈퇴
//@Service
public interface UserService {
    User updateUser(Long userId, User updatedUser);
    void deleteUser(Long userId);
    public Optional<User> getUserById(Long userId);
}
