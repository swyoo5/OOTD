package org.pgm.ootdproject.service;

import org.pgm.ootdproject.DTO.BoardDTO;
import org.pgm.ootdproject.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    public Optional<BoardDTO> readBoard(Long id);

//    public List<BoardDTO> readAllBoards();

    void deleteBoard(Long boardId);

    List<BoardDTO> readBoardsByUserId(Long userId);

    void increaseVisitCount(Long boardId);

    Optional<BoardDTO> findById(Long boardId);

    void saveBoard(BoardDTO boardDTO);
}
