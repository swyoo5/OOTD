package org.pgm.ootdproject.repository;

import org.junit.jupiter.api.Test;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.entity.BookMark;
import org.pgm.ootdproject.entity.BookMarkId;
import org.pgm.ootdproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BookmarkRepositoryTest {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            User user = userRepository.save(User.builder()
                    .loginId("user1")
                    .password("password1")
                    .email("user1@example.com")
                    .nickname("nickname1")
                    .build());

            Board board = boardRepository.save(Board.builder()
                    .title("Test Board")
                    .content("Test Content")
                    .user(user)
                    .build());

            BookMarkId bookMarkId = new BookMarkId(board.getBoardId(), user.getUserId());
            BookMark bookmark = BookMark.builder()
                    .id(bookMarkId)
                    .user(user)
                    .board(board)
                    .bookmarkedDate(LocalDateTime.now())
                    .build();
            BookMark result = bookmarkRepository.save(bookmark);
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
