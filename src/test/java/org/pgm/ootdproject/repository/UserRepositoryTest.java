package org.pgm.ootdproject.repository;

import org.junit.jupiter.api.Test;
import org.pgm.ootdproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest

public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            User user = User.builder()
                    .loginId("id..." + i)
                    .password("password..." + i)
                    .email("email..." + i)
                    .profileImage("image..." + i)
                    .build();
            User result = userRepository.save(user);
        });
    }

    @Test
    public void testSelect() {
        Long id = 1L;

        Optional<User> result = userRepository.findById(id);

        User user = result.orElseThrow();

        System.out.println(user);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Optional<User> result = userRepository.findById(id);
        User user = result.orElseThrow();
        user.change("id...100", "pw...100", "email...100", "image...100");
        userRepository.save(user);
    }

    @Test
    public void testDelete() {
        Long id = 11L;
        Optional<User> result = userRepository.findById(id);
        User user = result.orElseThrow();
        userRepository.delete(user);
    }
}
