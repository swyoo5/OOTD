package org.pgm.ootdproject.repository;

import org.pgm.ootdproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId); // 로그인 id로 사용자를 찾아냄
}
