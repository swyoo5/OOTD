package org.pgm.ootdproject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsert() {
        User user = userRepository.save(User.builder()
                .loginId("user1")
                .email("user1@example.com")
                .nickname("nickname1")
                .password("testPassword")
                .build());

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Board board = Board.builder()
                    .user(user)
                    .title("title" + i)
                    .build();
            Board result = boardRepository.save(board);
        });
    }

    @Test
    public void testSelect() {
        Long id = 1L;

        Optional<Board> result = boardRepository.findById(id);

        Board board = result.orElseThrow();

        System.out.println(board);
    }
}
