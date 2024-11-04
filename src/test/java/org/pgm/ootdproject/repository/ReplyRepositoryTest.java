package org.pgm.ootdproject.repository;

import org.junit.jupiter.api.Test;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.entity.Reply;
import org.pgm.ootdproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {

        User user = userRepository.save(User.builder()
                .loginId("user1")
                .email("user1@example.com")
                .nickname("nickname1")
                .password("password1")
                .build());
        Board board = boardRepository.save(Board.builder()
                .title("Test Board")
                .user(user)
                .content("Test Content")
                .build());


        IntStream.rangeClosed(1, 10).forEach(i -> {
            Reply reply = Reply.builder()
                    .content("내용" + i)
                    .board(board)
                    .user(user)
                    .isDeleted(false)
                    .build();
            Reply result = replyRepository.save(reply);
        });
    }
}
