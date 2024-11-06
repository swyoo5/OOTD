package org.pgm.ootdproject.repository;

import org.junit.jupiter.api.Test;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.entity.User;
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


        IntStream.rangeClosed(1, 10).forEach(i -> {
            User user = userRepository.save(User.builder()
                    .loginId("user" + i)
                    .email("user" + i + "@example.com")
                    .nickname("nickname1" + i)
                    .password("testPassword" + i)
                    .build());

            Board board = Board.builder()
                    .user(user)
                    .title("title" + i)
                    .popularityScore(1)
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

    @Test
    public void testDelete() {
        Long id = 12L;
        boardRepository.deleteById(id);
    }
}
