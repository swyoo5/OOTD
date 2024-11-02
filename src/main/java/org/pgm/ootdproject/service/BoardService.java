package org.pgm.ootdproject.service;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    public Optional<Board> readBoard(Long id);

    List<Board> readAllBoards();

    void deleteBoard(Long boardId);
}
